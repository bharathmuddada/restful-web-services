package com.qa.rest.webservices.restfulwebservices;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.api.errors.*;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.TextProgressMonitor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class testRunController {


    @RequestMapping(method = RequestMethod.GET, path = "/run-test")
    public void runTest() throws GitAPIException, IOException {

        String repo_dir = "C:\\Users\\Public\\JGIT_TESTING\\test";

        File localRepoDir = new File(repo_dir);

        Path path = Paths.get(repo_dir);
        if(Files.exists(path)){
            deleteDirectory(localRepoDir);
            localRepoDir.delete();
        }

        //create directory
        File NewlocalRepoDir = new File(repo_dir);
        // Monitor to get git command progress printed on java System.out console
        TextProgressMonitor consoleProgressMonitor = new TextProgressMonitor(new PrintWriter(System.out));


        System.out.println("\n>>> Cloning repository\n");
//        Repository repo = Git.cloneRepository().setProgressMonitor(consoleProgressMonitor).setDirectory(localRepoDir)
//                .setURI("https://github.com/bharathmuddada/serenity-junit-tutorial").call().getRepository();

        String Remoteurl = "https://github.com/bharathmuddada/JunitStarterProject.git";
        Repository repo = Git.cloneRepository().setProgressMonitor(consoleProgressMonitor).setDirectory(localRepoDir)
                .setURI(Remoteurl).call().getRepository();


        try (Git git = new Git(repo)) {
            System.out.println("\n>>> Listing all branches\n");
            git.branchList().setListMode(ListMode.ALL).call().stream().forEach(r -> System.out.println(r.getName()));

            Optional<String> developBranch = git.branchList().setListMode(ListMode.REMOTE).call().stream()
                    .map(r -> r.getName()).filter(n -> n.contains("enhancement")).findAny();

            if (developBranch.isPresent()) {
                System.out.println("\n>>> Checking out develop branch\n");
//                git.checkout().setProgressMonitor(consoleProgressMonitor).setCreateBranch(true).setName("local-master")
//                        .setStartPoint(developBranch.get()).call();
                String branch_name = developBranch.get();
                String finalbranch = branch_name.split("/")[3];
                System.out.println(finalbranch);
                git.checkout().setProgressMonitor(consoleProgressMonitor).setName(developBranch.get()).call();

            }
        }

        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd c: && cd C:\\Users\\Public\\JGIT_TESTING\\test && dir");

        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }

    }

    public static void deleteDirectory(File file)
    {
        // store all the paths of files and folders present
        // inside directory
        for (File subfile : file.listFiles()) {

            // if it is a subfolder,e.g Rohan and Ritik,
            // recursiley call function to empty subfolder
            if (subfile.isDirectory()) {
                deleteDirectory(subfile);
            }

            // delete files and empty subfolders
            subfile.delete();
        }
    }

}