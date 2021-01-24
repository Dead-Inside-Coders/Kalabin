using System;
using System.IO;
using System.Text.RegularExpressions;

namespace Email
{
    class Program
    {
        static void Main(string[] args)
        {
            StreamReader sr = new StreamReader("email.txt", System.Text.Encoding.UTF8);
            string text = sr.ReadToEnd();

            Regex r = new Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+.[a-zA-Z]{2,4}"); 
            MatchCollection me = r.Matches(text);

            for (int i = 0; i < me.Count; i++)
                Console.WriteLine(me[i].Value);

            Console.ReadKey();

        }
    }
}
