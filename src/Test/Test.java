package Test;

import Allocation.EqualAllocation;
import Allocation.FaultFrequencyAllocation;
import Allocation.LocalityModelAllocation;
import Allocation.ProportionalAllocation;
import Components.CPU;
import Components.Page;
import utils.Generator;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

        int processesNumber = 10;
        int numberOfQueries = 10000;
        int RAMSize = 100;

        System.out.println("___________________________________________");
        System.out.println("frame-allocations");
        System.out.println("processes number:"+processesNumber);
        System.out.println("number of queries:"+numberOfQueries);
        System.out.println("number of frames:" + RAMSize);



        Generator generator = new Generator(numberOfQueries,processesNumber);
        ArrayList<Page> pageQueries = generator.getGeneratedPages();

        System.out.println("___________________________________________");
        System.out.println("Equal:");
        CPU cpu = new CPU(pageQueries,RAMSize,generator.getProcesses(),new EqualAllocation());
        cpu.execute();
        System.out.println("___________________________________________");
        System.out.println("Proportional:");
        cpu.setFrameAllocation(new ProportionalAllocation());
        cpu.execute();
        System.out.println("___________________________________________");
        System.out.println("Page fault frequency control:");
        cpu.setFrameAllocation(new FaultFrequencyAllocation());
        cpu.execute();
        System.out.println("___________________________________________");
        System.out.println("Locality Model:");
        cpu.setFrameAllocation(new LocalityModelAllocation());
        cpu.execute();
        System.out.println("___________________________________________");
    }
}
