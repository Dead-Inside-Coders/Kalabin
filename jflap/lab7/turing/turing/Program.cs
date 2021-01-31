using System;
using System.Collections;
using System.Runtime.InteropServices;


namespace turing
{
    class TuringMachine
    {
        [DllImport ("user32.dll", EntryPoint = "GetKeyState")]
        public static extern short GetKeyState(int nVirtKey);
        struct LeftSide
        {
            public int state;
            public char symbol;

            public LeftSide(int st,char sym)
            {
                state = st;
                symbol = sym;

            }

            public override bool Equals(object obj)
            {
                return obj is LeftSide side &&
                       state == side.state &&
                       symbol == side.symbol;
            }

            public override int GetHashCode()
            {
                int hashCode = 58075447;
                hashCode = hashCode * -1521134295 + state.GetHashCode();
                hashCode = hashCode * -1521134295 + symbol.GetHashCode();
                return hashCode;
            }
        }
        struct RightSide
        {
            public int state;
            public char symbol, command;

            public RightSide(int st,char sym, char c)
            {
                state = st;
                symbol = sym;
                command = c;
            }

            public override bool Equals(object obj)
            {
                return obj is RightSide side &&
                       state == side.state &&
                       symbol == side.symbol &&
                       command == side.command;
            }

            public override int GetHashCode()
            {
                int hashCode = 2134386561;
                hashCode = hashCode * -1521134295 + state.GetHashCode();
                hashCode = hashCode * -1521134295 + symbol.GetHashCode();
                hashCode = hashCode * -1521134295 + command.GetHashCode();
                return hashCode;
            }
        }
        static void Main(string[] args)
        {
            int Sstate = Convert.ToInt32(Console.In.ReadLine());
            int Ystate = Convert.ToInt32(Console.In.ReadLine());
            int Nstate = Convert.ToInt32(Console.In.ReadLine());

            string Tape = ">" + (string)Console.In.ReadLine() + "_";

            Hashtable rules = new Hashtable();
            string s;

            while ((s = Console.In.ReadLine()) != "")
            {
                string[] tr = s.Split(' ');

                rules.Add(new LeftSide(Convert.ToInt32(tr[0]), Convert.ToChar(tr[1])),
                    new RightSide(Convert.ToInt32(tr[2]), Convert.ToChar(tr[3]), Convert.ToChar(tr[4])));

            }
            int State = Sstate;
            int TapeIdx = 1;
            const int VK_SPACE = 0x20;
            try
            {
                while (State != Ystate && State != Nstate)
                {
                    if (((int)GetKeyState(VK_SPACE) & 0x8000) != 0)
                    {
                        Console.WriteLine("Работа прервана");
                        return;
                    }

                    RightSide r = (RightSide)rules[new LeftSide(State, Tape[TapeIdx])];
                    State = r.state;
                    Tape = (Tape.Remove(TapeIdx, 1)).Insert(TapeIdx, r.symbol.ToString());
                    if (r.command == 'L')
                    {
                        TapeIdx--;
                    }
                    else if (r.command == 'R')
                    {
                        TapeIdx++;
                    }
                    if (TapeIdx >= Tape.Length)
                    {
                        Tape += "_";
                    }
                }
                Console.WriteLine(State == Ystate ? "Строка допущена" : "Строка отвергнута");
            } catch (Exception)
            {
                Console.WriteLine("Строка отвергнута");
            }

            Console.WriteLine("Лента: " + Tape);

            Console.ReadKey();

        }
    }
}
