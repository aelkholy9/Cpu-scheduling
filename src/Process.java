import java.awt.image.BufferStrategy;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
import javax.swing.text.html.parser.Entity;
 
public class Process implements Comparable <Process>{
	static Scanner scan = new Scanner(System.in);
	public String name;
	public int arrivalTime,burstTime,Priority,quantam;
	public Process(String name,int arrivalTime,int burstTime,int Priority,int quantam)
	{
		this.name = name;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.Priority = Priority;
		this.quantam = quantam;
	}
        public int compareTo(Process CommpareProcess)
    {
        int compareQuantity = ((Process) CommpareProcess).arrivalTime;
        return this.arrivalTime - compareQuantity;
    }

        
	public static void SJF(Process[] processes,int context_switch)
	{
		System.out.println("-----SJF Schedulinig----");
		String excutingOrder="";
		int[] endTime = new int[processes.length];
		int[]burstTimeCopy=new int[processes.length];
		int runningTime=0;
		for (int x=0;x<processes.length;x++)
			runningTime+=processes[x].burstTime; // total time to finish all processes
		runningTime+=context_switch*((processes.length));
		for (int x=0;x<processes.length;x++) // make a copy of burst time
			burstTimeCopy[x] = processes[x].burstTime;
		int currentProcess=0; // runnable process
		//get highest priority (initially start with the arrival time 0)
 
		// run now
		//System.out.println("run: " + runningTime + '\n');
		int oldProcess=0;
		for (int x=0;x<runningTime;x++)
		{	
			//assigning currentProcess
			for (int y=0;y<processes.length;y++)
				{
				if (processes[y].burstTime>0)
				{	// assign any process as initial state if burst time > 0
					currentProcess = y;
					break;
					}
				if (y==processes.length-1)
					return;
		}
 
 
 
			// the variable x here represents the current second
 
			for (int y=0;y<processes.length;y++)
				if (processes[y].arrivalTime<=x && processes[y].burstTime<processes[currentProcess].burstTime && processes[y].burstTime>0)
					currentProcess = y;
 
 
			if (x==0)
				oldProcess = currentProcess;
 
			else if (oldProcess!=currentProcess)
			{
				oldProcess=currentProcess;			
				x+=context_switch;
				for (int i=0;i<context_switch;i++)
					excutingOrder+=" | ";
			}
 
			if (x!=runningTime-1)
				excutingOrder+=processes[currentProcess].name + " --> ";
 
			else
				excutingOrder+=processes[currentProcess].name;	
			processes[currentProcess].burstTime-=1;
			// get end time for each process to get the waiting time
			if (processes[currentProcess].burstTime==0)
						endTime[currentProcess]=x+1;
 
 
		}
		for (int x=0;x<processes.length;x++) // raga3 el copy tnay fl original w 3mlt el 7rka el ghabya de 3shan kaselt aghyr el code
			 processes[x].burstTime = burstTimeCopy[x] ;
 
		// ***** Print Excution Order *****
		System.out.println("-Excution Order: " + excutingOrder);
		// ***** Print waiting time for each process ******
		System.out.println("-Waiting Time For Each Process:");
		float totalWaiting=0;
		for (int x=0;x<endTime.length;x++)
			{
				System.out.println(processes[x].name+": "+(endTime[x]-processes[x].arrivalTime-processes[x].burstTime));
				totalWaiting+= endTime[x]-processes[x].arrivalTime-processes[x].burstTime;
			}
		// ***** Print turn around time for each process *****
		System.out.println("-Turn Around Time For Each Process:");
 
		float totalTA=0;
		for (int x=0;x<endTime.length;x++)
		{
			System.out.println(processes[x].name+": "+(endTime[x]-processes[x].arrivalTime));
			totalTA+= endTime[x]-processes[x].arrivalTime;
		}
		//***** Print average waiting time *******
		System.out.println("Avergae waiting time: " + totalWaiting/processes.length);
		// ****** Print average turn around time *****
		System.out.println("Avergae turn around time: " + totalTA/processes.length);
	}
 
 
 
 
 
 
	public static void priorityScheduling(Process[] processes)
	{
		System.out.println("-----Priority Schedulinig----");
		String excutingOrder="";
		int[] endTime = new int[processes.length];
		int[]burstTimeCopy=new int[processes.length];
		int runningTime=0;
		for (int x=0;x<processes.length;x++)
			runningTime+=processes[x].burstTime; // total time to finish all processes
		for (int x=0;x<processes.length;x++) // make a copy of burst time
			burstTimeCopy[x] = processes[x].burstTime;
		int currentProcess=0; // runnable process
		//get highest priority (initially start with the arrival time 0)
 
		// run now
		//System.out.println("run: " + runningTime + '\n');
 
		for (int x=0;x<runningTime;x++)
		{
			//assigning currentProcess
			for (int y=0;y<processes.length;y++)
				if (processes[y].burstTime>0) // assign any process as initial state if burst time > 0
				{
					currentProcess = y;
					break;
				}
 
			// the variable x here represents the current second
			for (int y=0;y<processes.length;y++)
				if (processes[y].Priority<processes[currentProcess].Priority && processes[y].arrivalTime<=x && processes[y].burstTime>0)
					currentProcess = y;
			if (x!=runningTime-1)
				excutingOrder+=processes[currentProcess].name + " --> ";	
			else
				excutingOrder+=processes[currentProcess].name;	
			processes[currentProcess].burstTime-=1;
			// get end time for each process to get the waiting time
			if (processes[currentProcess].burstTime==0)
					endTime[currentProcess]=x+1;
 
 
//			if (x>0 && x%10==0) // starvation solving
//				for (int i=0;i<processes.length;i++)
//					if (i!=currentProcess && processes[i].Priority>0)
//						processes[i].Priority-=1;
 
		}
		for (int x=0;x<processes.length;x++) // raga3 el copy tnay fl original w 3mlt el 7rka el ghabya de 3shan kaselt aghyr el code
			 processes[x].burstTime = burstTimeCopy[x] ;
 
		// ***** Print Excution Order *****
		System.out.println("-Excution Order: " + excutingOrder);
		// ***** Print waiting time for each process ******
		System.out.println("-Waiting Time For Each Process:");
		float totalWaiting=0;
		for (int x=0;x<endTime.length;x++)
			{
				System.out.println(processes[x].name+": "+(endTime[x]-processes[x].arrivalTime-processes[x].burstTime));
				totalWaiting+= endTime[x]-processes[x].arrivalTime-processes[x].burstTime;
			}
		// ***** Print turn around time for each process *****
		System.out.println("-Turn Around Time For Each Process:");
 
		float totalTA=0;
		for (int x=0;x<endTime.length;x++)
		{
			System.out.println(processes[x].name+": "+(endTime[x]-processes[x].arrivalTime));
			totalTA+= endTime[x]-processes[x].arrivalTime;
		}
		//***** Print average waiting time *******
		System.out.println("Avergae waiting time: " + totalWaiting/processes.length);
		// ****** Print average turn around time *****
		System.out.println("Avergae turn around time: " + totalTA/processes.length);
 
 
	}
 
 
	public static int ceil(int n,int div)
	{
 
		if (n%div!=0)
			return n/div +1;
		return n/div;
 
	}
	public static void AG(Process[] processes)
	{
		System.out.println("-----AG Schedulinig----");
		String excutingOrder="";
		int[] endtime = new int[processes.length];
		Deque<Integer>q = new ArrayDeque<Integer>();
		int minimumProcess=0;
		for (int x=0;x<processes.length;x++)
			if (processes[x].burstTime>0 && processes[x].arrivalTime<=0) // assign any process as initial state if burst time > 0
			{
				minimumProcess = x;
				break;
			}
 
		for (int x=0;x<processes.length;x++)
			if (processes[x].arrivalTime<processes[minimumProcess].arrivalTime && processes[minimumProcess].arrivalTime<=0) // get real minimum
				minimumProcess = x;
		q.add(minimumProcess); // initial state
		int time=-1;
		while (!q.isEmpty())
		{
			System.out.println("Quantam History: "+processes[q.peek()].name+": "+processes[q.peek()].quantam);
			//FCFS
			for (int c=0;c<processes.length;c++)
				{
				if (processes[c].burstTime!=0)
					break;
				else if (c==processes.length-1)
					return;
				}
 
			time++;
			int runtime=ceil(processes[q.peek()].quantam,4);
			int remaining=processes[q.peek()].quantam;
 
			for (int i=0;i<runtime;i++)
			{
				processes[q.peek()].burstTime-=1;
				excutingOrder+=processes[q.peek()].name+"-->";
				time++;
				if (processes[q.peek()].burstTime==0)
					{
					endtime[q.peek()]=time+1;
					break;
					
					}
			}
			remaining-=runtime;
			if (processes[q.peek()].burstTime<=0 || processes[q.peek()].quantam<1)
			{
				if (processes[q.peek()].quantam<1)
					{
						processes[q.peek()].quantam+=ceil(remaining,2);
						System.out.println("Quantam History: "+processes[q.peek()].name+": "+processes[q.peek()].quantam);
						int tmp = q.peekFirst();
						q.pollFirst();
						q.add(tmp);
						continue;
					}
				endtime[q.peek()]=time+1;
				q.pollFirst();
				continue;
			}
			// if not completed 
			// ***** Apply non premptive priority
			for (int x=0;x<processes.length;x++)
				if (processes[x].burstTime>0 && processes[x].arrivalTime<=time) // assign any process as initial state if burst time > 0
				{
					minimumProcess = x;
					break;
				}
 
			for (int x=0;x<processes.length;x++)
				if (processes[x].Priority<processes[minimumProcess].Priority && processes[x].arrivalTime<=time) // get real minimum
					{
						minimumProcess=x;
					}
			 if (processes[minimumProcess].Priority!=processes[q.peek()].Priority && processes[minimumProcess].Priority<processes[q.peek()].Priority)
			 {
					processes[q.peek()].quantam+=ceil(processes[q.peek()].quantam-runtime,2);
					System.out.println("Quantam History: "+processes[q.peek()].name+": "+processes[q.peek()].quantam);
		 			int tmp = q.peekFirst();
		 			q.pollFirst();
		 			q.add(tmp);
		 			q.offerFirst(minimumProcess);
		 			continue; 
			 }
 
			// if it continues as it has the highest priority
			// ***** Apply non premptive priority
 
			 for (int i=0;i<runtime;i++)
				{
					processes[q.peek()].burstTime-=1;
					excutingOrder+=processes[q.peek()].name+"-->";
					time++;
					if (processes[q.peek()].burstTime==0)
						{
						endtime[q.peek()]=time+1;
						break;
						
						}
				}
 
			if (processes[q.peek()].burstTime<=0 || processes[q.peek()].quantam<1)
			{
				if (processes[q.peek()].quantam<1)
					{
						processes[q.peek()].quantam+=2;
						System.out.println("Quantam History: "+processes[q.peek()].name+": "+processes[q.peek()].quantam);
						int tmp = q.peekFirst();
						q.pollFirst();
						q.add(tmp);
						continue;
					}
 
				endtime[q.peek()]=time+1;
				q.pollFirst();
				continue;
			}
 
			// try to apply SJF
			for (int x=0;x<processes.length;x++)
				if (processes[x].burstTime>0 && processes[x].arrivalTime<=time) // assign any process as initial state if burst time > 0
				{
					minimumProcess = x;
					break;
				}
 
			for (int x=0;x<processes.length;x++)
				if (processes[x].burstTime<processes[minimumProcess].burstTime && processes[x].arrivalTime<=time) // get real minimum
					{
						minimumProcess=x;
					}
 
			 if (processes[minimumProcess].burstTime!=processes[q.peek()].burstTime && processes[minimumProcess].burstTime<processes[q.peek()].burstTime)
			 {
					processes[q.peek()].quantam+=remaining;
					System.out.println("Quantam History: "+processes[q.peek()].name+": "+processes[q.peek()].quantam);
		 			int tmp = q.peekFirst();
		 			q.pollFirst();
		 			q.add(tmp);
		 			q.offerFirst(minimumProcess);
		 			System.out.println("******** " + processes[minimumProcess].name + "*****" + time);
		 			continue; 
			 }
 
			 // if the  current process has lowest burst time
			 runtime = remaining;
			 for (int i=0;i<runtime;i++)
				{
					processes[q.peek()].burstTime-=1;
					excutingOrder+=processes[q.peek()].name+"-->";
					time++;
					if (processes[q.peek()].burstTime==0)
						{
							endtime[q.peek()]=time+1;
							break;							
						}
				}
			 q.pollFirst();
 
		}
		System.out.println("Excuting order: "+excutingOrder);
		//waiting time for each process
		int totalWA=0,totalTA=0;
		System.out.println("Waiting time for each process: ");
		for (int x=0;x<processes.length;x++)
			{
			System.out.println(processes[x].name+": "+(endtime[x]-processes[x].arrivalTime-processes[x].burstTime));
			totalWA+=endtime[x]-processes[x].arrivalTime-processes[x].burstTime;
			}
		//roundtime for each process
		System.out.println("turn around time for each process: ");
		for (int x=0;x<processes.length;x++)
			{
			System.out.println(processes[x].name+": "+(endtime[x]-processes[x].arrivalTime));
			totalTA+=endtime[x]-processes[x].arrivalTime;
			}
		//average waiting time
		System.out.println("Average waiting time: "+totalWA/processes.length);
		//average turn around time
		System.out.println("Average turn around time: "+totalTA/processes.length);
		
	}
 
 public static void RR(Process p[])
    {System.out.println("-----RR Schedulinig----");
        Scanner sc=new Scanner(System.in);
       System.out.println("Enter Time Quantum");
       int qt=sc.nextInt();
       String order="";
       int curTime=0;
       int comp[]=new int[p.length];
       int wt[]=new int[p.length];
       int rbt[]=new int[p.length];
       int art[]=new int[p.length];

       Arrays.sort(p);
       for (int i=0;i<p.length;i++)
       {
           rbt[i]=p[i].burstTime;
           art[i]=p[i].arrivalTime;
       }

       while(true)
       {
           boolean done=true;
           for (int i=0;i<p.length;i++)
           {
               if(art[i]<=curTime){
                   if(art[i]<=qt){
                       if(rbt[i]>0){
                           done= false;
                           if (rbt[i]>qt){
                               curTime+=qt;
                               rbt[i]-=qt;
                               art[i]+=qt;
                               order+="->"+p[i].name;
                           }
                           else{
                               curTime+=rbt[i];
                               comp[i]=curTime-p[i].arrivalTime;
                               wt[i]=curTime-p[i].burstTime-p[i].arrivalTime;
                               rbt[i]=0;
                               order+="->"+p[i].name;
                           }
                       }
                   }
                   else if(art[i]>qt){
                       for (int j=0;j<p.length;j++){
                           if (art[j]<art[i]){
                               if (rbt[j]>0){
                                   done= false;
                                   if(rbt[j]>qt){
                                       curTime+=qt;
                                       rbt[j]-=qt;
                                       art[j]+=qt;
                                       order+="->"+p[j].name;
                                   }
                                   else{
                                       curTime+=rbt[j];
                                       comp[j]=curTime-p[j].arrivalTime;
                                       wt[j]=curTime-p[j].burstTime-p[j].arrivalTime;
                                       rbt[j]=0;
                                       order+="->"+p[j].name;
                                   }
                               }
                           }
                       }
                       if (rbt[i]>0){
                           done= false;
                           if (rbt[i]>qt){
                               curTime+=qt;
                               rbt[i]-=qt;
                               art[i]+=qt;
                               order+="->"+p[i].name;
                           }
                           else{
                               curTime+=rbt[i];
                               comp[i]=curTime-p[i].arrivalTime;
                               wt[i]=curTime-p[i].burstTime-p[i].arrivalTime;
                               rbt[i]=0;
                               order+="->"+p[i].name;
                           }
                       }
                   }
               }
               else if(art[i]>curTime){
                   curTime++;
                   i--;
               }
           }
           if (done)
               break;
       }
       double twt=0,ttat=0,avg1=0,avg2=0;
       for (int i=0;i<p.length;i++)
       {
           System.out.println("Waiting time of "+p[i].name+" is "+wt[i]+" TAT "+comp[i]);
           ttat+=comp[i];
           twt+=wt[i];
       }
       System.out.println(order);
       avg1=twt/p.length;
       avg2=ttat/p.length;
       System.out.println("Average waiting time= "+avg1);
       System.out.println("Average TAT= "+avg2);
    
}
 
	//start main
	public static void main(String args[])
	{
		System.out.println("Enter number of processes,Round Robin and Context Switching");
		int numberprocess = scan.nextInt();
		int RR = scan.nextInt();
		int CS = scan.nextInt();
		Process[] arr = new Process[numberprocess];
		for (int x=0;x<numberprocess;x++)
		{
			System.out.println("Enter Name,Arrival Time,Burst Time,Priority,Quantam for process: " + x);
			String n;
			int a,b,p,q;
			n=scan.next();
			a=scan.nextInt();
			b=scan.nextInt();
			p=scan.nextInt();
			q=scan.nextInt();
			Process pnew = new Process(n,a, b,p,q);
			arr[x]=pnew;
		}
		SJF(arr, CS);
		RR(arr);
		priorityScheduling(arr);
		AG(arr);
	}
}