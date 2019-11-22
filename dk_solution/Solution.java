import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
 
public class Solution {
    public static void main(String[] args) {
        ConsoleProcessor processor = new ConsoleProcessor();
        processor.processAllLines();
    }
}
 
class ConsoleProcessor {
 
    public OrgChart orgChart = new OrgChart();
 
    public void processAllLines() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
 
        Integer numLines = 0;
 
        try {
           numLines = Integer.valueOf(line.trim());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
 
        for (int i = 0; i < numLines; i++) {
            processLine(in.nextLine());
        }
 
        in.close();
    }
 
    protected void processLine(String line) {
        String[] parsedCommand = line.split(",");
 
        // ignore empty lines
        if (parsedCommand.length == 0) {
            return;
        }
 
        switch (parsedCommand[0]) {
            case "add":
                orgChart.add(parsedCommand[1], parsedCommand[2], parsedCommand[3]);
                break;
            case "print":
                orgChart.print();
                break;
            case "remove":
                orgChart.remove(parsedCommand[1]);
                break;
            case "move":
                orgChart.move(parsedCommand[1], parsedCommand[2]);
                break;
            case "count":
                System.out.println(orgChart.count(parsedCommand[1]));
                break;
        }
    }
}
 
class OrgChart {
    class Node{
	String name;
	String id;
	String managerId;
	List<Node> nodes = new ArrayList<Node>();
	public Node(String id, String name, String managerId){
		this.name = name;
		this.id = id;
		this.managerId = managerId;
	}
    }
	Map<String, Node> hash = new HashMap<String, Node>();
	Node head = new Node("-1","head",null);
	public OrgChart() {
		hash.put(head.id, head);
	}
    public void add(String id, String name, String managerId) 
    {
	if (!hash.containsKey(id)){
		if (!hash.containsKey(managerId)) managerId = "-1";
		Node newPerson = new Node(id, name, managerId);
		hash.get(managerId).nodes.add(newPerson);
		hash.put(id,newPerson);
	}
    }
 
    public void print() 
    {
	for (Node node : head.nodes){
		dfsPrint(node,"");
	}
    }
	public void dfsPrint(Node node,String blank){
		StringBuilder sb = new StringBuilder();
		sb.append(blank).append(node.name).append(" [").append(node.id).append("]");
		System.out.println(sb.toString());
		if (node.nodes.size() == 0) return;
		for (Node child : node.nodes){
			blank = blank + "  ";
			dfsPrint(child, blank);
			blank = blank.substring(2);
		}
	
	}
 
    public void remove(String employeeId) 
    {
	if (hash.containsKey(employeeId)){
		Node employee = hash.get(employeeId);
		
		String managerId = employee.managerId;
		if (!hash.containsKey(managerId)) managerId = "-1";
		Node manager = hash.get(managerId);
		manager.nodes.remove(employee);
		for (Node node : employee.nodes){
			manager.nodes.add(node);
			node.managerId = managerId;
		}
		hash.remove(employeeId);
	}
    }
 
    public void move(String employeeId, String newManagerId) 
    {
	if (hash.containsKey(employeeId) && hash.containsKey(newManagerId)){
		Node employee = hash.get(employeeId);
		Node newManager = hash.get(newManagerId);
		Node oldManager = hash.get(employee.managerId);
		oldManager.nodes.remove(employee);
		newManager.nodes.add(employee);
		employee.managerId = newManagerId;
	}
    }
 
    public int count(String employeeId) 
    {
	 if (hash.containsKey(employeeId)){
		Node employee = hash.get(employeeId);
		int count = dfsCount(employee);
		return count - 1;
	}
	return -1;
    }
	public int dfsCount(Node person){
		if (person.nodes.size() == 0) return 1;
		int addition = 0;
		for (Node node : person.nodes){
			addition += dfsCount(node);
		}
		return addition + 1;
	}
}


