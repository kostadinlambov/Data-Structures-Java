package core;

import model.File;
import model.SampleFile;
import shared.FileManager;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FileExplorer implements FileManager {
    private File root;
    private Deque<File> buffer;

    public FileExplorer() {
        this.root = new SampleFile(1, "Root");
        this.buffer = new ArrayDeque<>();
    }

    @Override
    public void addInDirectory(int directorNumber, File file) {
        addFileToDirectory(this.root, directorNumber, file);
    }

    private void addFileToDirectory(File currentNode, int directorNumber, File file) {
        if (currentNode.getNumber() == directorNumber) {
            currentNode.getChildren().add(file);
            file.setParent(currentNode);
            return;
        }

        Deque<File> fileDeque = new ArrayDeque<>();

        fileDeque.addLast(currentNode);

        while (!fileDeque.isEmpty()) {
            File currentFirst = fileDeque.pollFirst();

            if (currentFirst.getNumber() == directorNumber) {
                currentFirst.getChildren().add(file);
                file.setParent(currentFirst);
                return;
            }

            for (File currentChild : currentFirst.getChildren()) {
                fileDeque.addLast(currentChild);
            }
        }

        throw new IllegalStateException();
    }

    @Override
    public File getRoot() {
        return this.root;
    }

    @Override
    public File get(int number) {
        return getFileWithNumber(this.root, number);
    }

    private File getFileWithNumber(File currentFile, int number) {
        if (currentFile.getNumber() == number) {
            return currentFile;
        }

        Deque<File> fileDeque = new ArrayDeque<>();

        fileDeque.addLast(currentFile);

        while (!fileDeque.isEmpty()) {
            File currentFirst = fileDeque.pollFirst();

            if (currentFirst.getNumber() == number) {
                return currentFirst;
            }

            for (File currentChild : currentFirst.getChildren()) {
                fileDeque.addLast(currentChild);
            }
        }

        throw new IllegalStateException();
    }

    @Override
    public Boolean deleteFile(File file) {
        return removeFile(this.root, file);
    }

    private Boolean removeFile(File currentFile, File file) {
        if (currentFile.equals(file)) {
            currentFile.setGetChildren(new ArrayList<>());

            File parent = currentFile.getParent();
            if(parent != null){
                currentFile.getParent().getChildren().remove(file);
                currentFile.setParent(null);
            }

            return true;
        }

        Deque<File> fileDeque = new ArrayDeque<>();

        fileDeque.addLast(currentFile);

        while (!fileDeque.isEmpty()) {
            File currentFirst = fileDeque.pollFirst();

            if (currentFirst.equals(file)) {
                currentFirst.setGetChildren(new ArrayList<>());
                File parent = currentFirst.getParent();

                if(parent != null){
                    parent.getChildren().remove(file);
                    currentFirst.setParent(null);
                }

                return true;

            }

            for (File currentChild : currentFirst.getChildren()) {
                fileDeque.addLast(currentChild);
            }
        }

        return false;
    }

    @Override
    public List<File> getFilesInPath(File path) {
        List<File> result = new ArrayList<>();

        return getAllFiles(this.root, path, result);
    }

    private List<File> getAllFiles(File currentFile, File path, List<File> result) {
        Deque<File> fileDeque = new ArrayDeque<>();

        fileDeque.addLast(currentFile);

        while (!fileDeque.isEmpty()) {
            File currentFirst = fileDeque.pollFirst();

            if (currentFirst.equals(path)) {
                return traverseAllFilesInPath(currentFirst, result);
            }

            for (File currentChild : currentFirst.getChildren()) {
                fileDeque.addLast(currentChild);
            }
        }

        return result;
    }

    private List<File> traverseAllFilesInPath(File currentFile, List<File> result) {
        List<File> children = currentFile.getChildren();
        Deque<File> fileDeque = new ArrayDeque<>(children);

        while (!fileDeque.isEmpty()) {
            File currentFirst = fileDeque.pollFirst();

            result.add(currentFirst);

            for (File currentChild : currentFirst.getChildren()) {
                fileDeque.addLast(currentChild);
            }
        }

        return result;
    }

    @Override
    public void move(File file, File destination) {
        if(file.equals(this.root)){
            throw new IllegalStateException();
        }

        Boolean deleteFileSuccess = this.deleteFile(file);

        if(deleteFileSuccess){
            destination.getChildren().add(file);
            file.setParent(destination);
        }
    }

    @Override
    public Boolean contains(File file) {
        return searchForFile(this.root, file);
    }

    private Boolean searchForFile(File currentFile, File file) {
        if (currentFile.equals(file)) {
            return true;
        }

        Deque<File> fileDeque = new ArrayDeque<>();

        fileDeque.addLast(currentFile);

        while (!fileDeque.isEmpty()) {
            File currentFirst = fileDeque.pollFirst();

            if (currentFirst.equals(file)) {
                return true;
            }

            for (File currentChild : currentFirst.getChildren()) {
                fileDeque.addLast(currentChild);
            }
        }

        return false;
    }

    @Override
    public List<File> getInDepth() {
        List<File> result = new ArrayList<>();

        Deque<File> toTraverse = new ArrayDeque<>();

        toTraverse.addLast(this.root);

        while (!toTraverse.isEmpty()) {
            File currentNode = toTraverse.pollFirst();

            result.add(currentNode);


            for (File node : currentNode.getChildren()) {
                toTraverse.addLast(node);
            }
        }

        return result;
    }

    @Override
    public List<File> getInLevel() {
        List<File> result = new ArrayList<>();

        traverseRecursive(this.root, result);

        return result;
    }

    private void traverseRecursive(File currentFile, List<File> result) {
        for (File child : currentFile.getChildren()) {
            traverseRecursive(child, result);
        }

        result.add(currentFile);
    }

    @Override
    public void cut(int number) {
        File file = this.get(number);

        this.buffer.push(file);

        this.deleteFile(file);
    }

    @Override
    public void paste(File destination) {
        Boolean contains = this.contains(destination);
        if(!contains){
            throw new IllegalStateException();
        }

        File popFile = this.buffer.pop();

        destination.getChildren().add(popFile);
        popFile.setParent(destination);
    }

    @Override
    public Boolean isEmpty() {
        return this.root.getChildren().size() == 0;
    }

    @Override
    public String getAsString() {
        if (this.root == null) {
            return "";
        }
        StringBuilder buffer = new StringBuilder();
        print(this.root, buffer, "", "");
        return buffer.toString().trim();
    }

    private void print(File file, StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(file.getNumber());
        buffer.append(System.lineSeparator());
        List<File> children = file.getChildren();
        for (int i = 0; i < children.size(); i++) {
            File next = children.get(i);
            if (i < children.size() - 1) {
                print(next, buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            } else {
                print(next, buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }
}
