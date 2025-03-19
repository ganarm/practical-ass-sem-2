// 2. Missionaries and Cannibals (user input game type)

import java.util.*;
public class MaC
{
    public static void main(String[] args)
    {
        int LC=3;
        int LM=3;
        int RC=0;
        int RM=0;   
        int userC;
        int userM; 
        int flag=0;   
        Scanner sc=new Scanner(System.in);
        while(flag==0)
        {
            flag=0;
            if(RC==3 && RM==3)
            {
                System.out.println("Win!!!");
                break;
            }
            while(flag==0)
            {
                for (int i=0;i<LC;i++)
                {
                    System.out.print("C ");
                }
                System.out.print("                                         ");
                for (int i=0;i<RC;i++)
                {
                    System.out.print("C ");
                }
                System.out.println();
                System.out.println("===============================================");
                for (int i=0;i<LM;i++)
                {
                    System.out.print("M ");
                }
                System.out.print("                                         ");
                for (int i=0;i<RM;i++)
                {
                    System.out.print("M ");
                }
                System.out.println();
                // System.out.println("LC : "+LC+" & LM : "+LM+" && RC : "+RC+" & RM : "+RM);
                System.out.println("Enter Data for left to right : ");
                System.out.println("Enter No. of Canibals : ");
                userC=sc.nextInt();
                System.out.println("Enter No. of Missionaries : ");
                userM=sc.nextInt();

                if(userC+userM >2 || userC>LC || userM>LM)
                {
                    System.out.println("Plz.. enter valid input");
                    continue;
                }
                LC=LC-userC;
                LM=LM-userM;
                RC=RC+userC;
                RM=RM+userM;

                if ((LC>LM &&  LM>0) || (RC>RM && RM>0))
                {
                    System.out.println("Game Over!!!");
                    flag=1;
                    break;
                }
                break;
            }
            if(RC==3 && RM==3)
            {
                System.out.println("Win!!!");
                break;
            }
            while(flag==0)
            {
                for (int i=0;i<LC;i++)
                {
                    System.out.print("C ");
                }
                System.out.print("                                         ");
                for (int i=0;i<RC;i++)
                {
                    System.out.print("C ");
                }
                System.out.println();
                System.out.println("===============================================");
                for (int i=0;i<LM;i++)
                {
                    System.out.print("M ");
                }
                System.out.print("                                         ");
                for (int i=0;i<RM;i++)
                {
                    System.out.print("M ");
                }
                System.out.println();
                System.out.println("Enter Data for right to left : ");
                System.out.println("Enter No. of Canibals : ");
                userC=sc.nextInt();
                System.out.println("Enter No. of Missionaries : ");
                userM=sc.nextInt();

                if(userC+userM >2 || userC > RC || userM > RM)
                {
                    System.out.println("plz.. enter valid input");
                    continue;
                }
                LC=LC+userC;
                LM=LM+userM;
                RC=RC-userC;
                RM=RM-userM;
                if ((LC>LM &&  LM>0) || (RC>RM && RM>0))
                {
                    System.out.println("Game Over!!!");
                    flag=1;
                    break;
                }
                break;
            }
        }

    }
}
