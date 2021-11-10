using System;
using System.Text;

namespace RobotClient
{
    class Program
    {
        private static System.Net.Sockets.TcpClient client;
        private static Encoding ascii = Encoding.ASCII;

        static void Main(string[] args)
        {
        //client files
            //"192.168.43.1"
            Console.WriteLine("Type commands here type help for commands\nnot being able to type means you have not connected to the robot");
            client = new System.Net.Sockets.TcpClient("192.168.43.1", 25577);
            System.Net.Sockets.NetworkStream ns = client.GetStream();
            
            while (true)
            {
                String command = Console.ReadLine();
                if (command.ToLower().Equals("help"))
                {
                    Console.Out.WriteLine("setdirection adddirection setboth setright setleft addturnpower setturnpower reconnect disconnect help");
                    continue;
                }else if (command.ToLower().Equals("reconnect"))
                {
                    client.Close();
                    ns.Close();
                    client = new System.Net.Sockets.TcpClient("192.168.43.1", 25577);
                    ns = client.GetStream();
                    continue;
                }else if (command.ToLower().Equals("disconnect"))
                {
                    client.Close();
                    ns.Close();
                    continue;
                }
                byte[] bytes = ascii.GetBytes(command);
                ns.Write(bytes,0,bytes.Length);
                Console.WriteLine(bytes);
            }
        }
    }
}
