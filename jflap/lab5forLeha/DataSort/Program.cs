using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

public class TuringMachine
{
    public static void Main()
    {

        var sorter = new TuringMachine("A", '*', "X").WithTransitions(
            ("A", 'a', 'a', Right, "A"),
            ("A", 'b', 'B', Right, "B"),
            ("A", '*', '*', Left, "E"),
            ("B", 'a', 'a', Right, "B"),
            ("B", 'b', 'b', Right, "B"),
            ("B", '*', '*', Left, "C"),
            ("C", 'a', 'b', Left, "D"),
            ("C", 'b', 'b', Left, "C"),
            ("C", 'B', 'b', Left, "E"),
            ("D", 'a', 'a', Left, "D"),
            ("D", 'b', 'b', Left, "D"),
            ("D", 'B', 'a', Right, "A"),
            ("E", 'a', 'a', Left, "E"),
            ("E", '*', '*', Right, "X")
        )
        .WithInput("babbababaa");

        Console.WriteLine("babbababaa");
        sorter.Run().Last();
        Console.WriteLine("Sorted: " + sorter.TapeString);
        PrintResults(sorter);


        Console.WriteLine("bbbababaaabba");
        sorter.Reset().WithInput("bbbababaaabba");
        sorter.Run().Last();
        Console.WriteLine("Sorted: " + sorter.TapeString);
        PrintResults(sorter);

        void PrintResults(TuringMachine tm)
        {
            Console.WriteLine($"End state: {tm.State} = {(tm.Success ? "Success" : "Failure")}");
            Console.WriteLine(tm.Steps + " steps");
            Console.WriteLine("tape length: " + tm.TapeLength);
            Console.WriteLine();
        }

        Console.ReadKey();
    }

    public const int Left = -1, Stay = 0, Right = 1;
    private readonly Tape tape;
    private readonly string initialState;
    private readonly HashSet<string> terminatingStates;
    private Dictionary<(string state, char read), (char write, int move, string toState)> transitions;

    public TuringMachine(string initialState, char blankSymbol, params string[] terminatingStates)
    {
        State = this.initialState = initialState;
        tape = new Tape(blankSymbol);
        this.terminatingStates = terminatingStates.ToHashSet();
    }

    public TuringMachine WithTransitions(
        params (string state, char read, char write, int move, string toState)[] transitions)
    {
        this.transitions = transitions.ToDictionary(k => (k.state, k.read), k => (k.write, k.move, k.toState));
        return this;
    }

    public TuringMachine Reset()
    {
        State = initialState;
        Steps = 0;
        tape.Reset();
        return this;
    }

    public TuringMachine WithInput(string input)
    {
        tape.Input(input);
        return this;
    }

    public int Steps { get; private set; }
    public string State { get; private set; }
    public bool Success => terminatingStates.Contains(State);
    public int TapeLength => tape.Length;
    public string TapeString => tape.ToString();

    public IEnumerable<string> Run()
    {
        yield return State;
        while (Step()) yield return State;
    }

    public async Task<TimeSpan> TimeAsync(CancellationToken cancel = default)
    {
        var chrono = Stopwatch.StartNew();
        await RunAsync(cancel);
        chrono.Stop();
        return chrono.Elapsed;
    }

    public Task RunAsync(CancellationToken cancel = default)
        => Task.Run(() => {
            while (Step()) cancel.ThrowIfCancellationRequested();
        });

    private bool Step()
    {
        if (!transitions.TryGetValue((State, tape.Current), out var action)) return false;
        tape.Current = action.write;
        tape.Move(action.move);
        State = action.toState;
        Steps++;
        return true;
    }


    private class Tape
    {
        private List<char> forwardTape = new List<char>(), backwardTape = new List<char>();
        private int head = 0;
        private char blank;

        public Tape(char blankSymbol) => forwardTape.Add(blank = blankSymbol);

        public void Reset()
        {
            backwardTape.Clear();
            forwardTape.Clear();
            head = 0;
            forwardTape.Add(blank);
        }

        public void Input(string input)
        {
            Reset();
            forwardTape.Clear();
            forwardTape.AddRange(input);
        }

        public void Move(int direction)
        {
            head += direction;
            if (head >= 0 && forwardTape.Count <= head) forwardTape.Add(blank);
            if (head < 0 && backwardTape.Count <= ~head) backwardTape.Add(blank);
        }

        public char Current
        {
            get => head < 0 ? backwardTape[~head] : forwardTape[head];
            set
            {
                if (head < 0) backwardTape[~head] = value;
                else forwardTape[head] = value;
            }
        }

        public int Length => backwardTape.Count + forwardTape.Count;

        public override string ToString()
        {
            int h = (head < 0 ? ~head : backwardTape.Count + head) * 2 + 1;
            var builder = new StringBuilder(" ", Length * 2 + 1);
            if (backwardTape.Count > 0)
            {
                builder.Append(string.Join(" ", backwardTape)).Append(" ");
                if (head < 0) (builder[h + 1], builder[h - 1]) = ('(', ')');
                for (int l = 0, r = builder.Length - 1; l < r; l++, r--) (builder[l], builder[r]) = (builder[r], builder[l]);
            }
            builder.Append(string.Join(" ", forwardTape)).Append(" ");
            if (head >= 0) (builder[h - 1], builder[h + 1]) = ('(', ')');
            return builder.ToString();
        }

    }

}
