using System;

namespace FA_Model
{
    class Program
    {
        static bool InMainRange(char ch)
        {
            return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') ||
                (ch >= '0' && ch <= '9') || (ch == '_' || ch == '_') ;
        }

        static void Main(string[] args)
        {
            string s = Console.ReadLine() + '\0';
            char state = '1';
            int i = 0;
            char symbol;
            try
            {
                while ((symbol = s[i++]) != '\0')
                {
                    switch (state)
                    {
                        case '1':
                            if (InMainRange(symbol) || symbol == '.')
                            {
                                state = '2';
                            }
                            else
                            {
                                throw new Exception();

                            }
                            break;
                        case '2':
                            if (InMainRange(symbol) || symbol == '.')
                            {
                                state = '2';
                            }
                            else if (symbol == '@')
                            {
                                state = '3';
                            }
                            else
                            {
                                throw new Exception();

                            }
                            break;
                        case '3':
                            if (InMainRange(symbol))
                            {
                                state = '4';
                            }
                            else
                            {
                                throw new Exception();

                            }
                            break;
                        case '4':
                            if (InMainRange(symbol))
                            {
                                state = '4';
                            }
                            else if (symbol == '.')
                            {
                                state = '5';
                            }
                            else
                            {
                                throw new Exception();
                            }
                            break;
                        case '5':
                            if (InMainRange(symbol))
                            {
                                state = 'F';
                            }
                            else if (symbol == '.')
                            {
                                state = '5';
                            }
                            else
                            {
                                throw new Exception();
                            }
                            break;

                    }
                }
                Console.WriteLine(state == 'F');
            }
            catch (Exception)
            {
                Console.WriteLine("обнаружен недопустимый символ");
            }
            Console.ReadKey();
        }
    }
}
