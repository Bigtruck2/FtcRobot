using System;
namespace RobotClient
{
    class Program
    {
        private static System.Net.Sockets.TcpClient client;

        static void Main(string[] args)
        {
            System.Collections.Generic.Dictionary<string, byte> dictionary = new System.Collections.Generic.Dictionary<string, byte>();
            fillDictionary(dictionary);
            String ip = "192.168.43.1";
            //"192.168.43.1"
            Console.WriteLine("Type commands here type help for commands\ntype something to connect");
            Console.ReadLine();
            client = new System.Net.Sockets.TcpClient(ip, 25577);
            System.Net.Sockets.NetworkStream ns = client.GetStream();

            while (true)
            {
                String command = Console.ReadLine();
                if (command.ToLower().Equals("help"))
                {
                    Console.Out.WriteLine("setdirection adddirection setboth setright setleft addturnpower setturnpower reconnect disconnect help");
                    continue;
                }
                else if (command.ToLower().Equals("reconnect"))
                {
                    client.Close();
                    ns.Close();
                    client = new System.Net.Sockets.TcpClient(ip, 25577);
                    ns = client.GetStream();
                    continue;
                }
                else if (command.ToLower().Equals("disconnect"))
                {
                    client.Close();
                    ns.Close();
                    continue;
                }
                double value;
                //gets the double at the end of the command
                Boolean parsable = Double.TryParse(command.Substring(command.IndexOf(' ') + 1), out value);
                if (!parsable)
                {
                    continue;
                }
                //takes in input converts it to binary then converts that to bytes
                //floating point integer so the doubles are represented as whole numbers and converted back to doubles on the other end
                //bytes 1 and 2 are for the number and its decimals and byte 3 is for the commands id

                //gets command part of command
                command = command.Substring(0, command.IndexOf(' '));
                //creates byte array and asigns last byte to the command id
                byte[] bytes = new byte[3];
                byte commandId;
                dictionary.TryGetValue(command, out commandId);
                if (value < 0)
                {
                    value *= -1;
                    bytes[2] = (byte)(commandId + 128) ;
                }
                else
                {
                    bytes[2] = commandId;
                }
                //converts double to floating point integer
                int byteNum = (int)(value * 128+.5);
                Console.WriteLine(byteNum);
                bytes[0] = (byte)(byteNum&0xFF);
                bytes[1] = (byte)((byteNum >> 8) & 0xFF);
               
                ns.Write(bytes, 0, bytes.Length);
            }
        }
            static void fillDictionary(System.Collections.Generic.Dictionary<string, byte> dictionary)
            {
            //hashmap of commands and there bytes
                dictionary.Add("setdirection", 0);
                dictionary.Add("adddirection", 1);
                dictionary.Add("setboth", 2);
                dictionary.Add("setright", 3);
                dictionary.Add("setleft", 4);
                dictionary.Add("addturnpower", 5);
                dictionary.Add("setturnpower", 6);
            }
           
    }
}

