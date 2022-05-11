package utils;

import Components.Page;
import Components.Process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Generator {

    private final int NUMBER_OF_PAGES_MAX = 10;
    private int queriesNumber;
    private int processesNumber;
    private Random r = new Random();
    private ArrayList<Page> generatedPages = new ArrayList<>();
    private ArrayList<Process> processes = new ArrayList<>();

    public Generator(int queriesNumber, int processesNumber) {
        this.queriesNumber = queriesNumber;
        this.processesNumber = processesNumber;
    }
    public ArrayList<Page> getGeneratedPages(){
        ArrayList<Page> pool = new ArrayList<>();
        for(int i = 0; i < processesNumber;i++){
            Process process = new Process();
            process.generatePages(r.nextInt(NUMBER_OF_PAGES_MAX));
            processes.add(process);
            for(Page page: process.getLogicalMemory()){
                pool.add(page);
            }
        }
        for(int i = 0; i < queriesNumber;i++){
            generatedPages.add(pool.get(r.nextInt(0,pool.size())));
        }
        return generatedPages;
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }
}
