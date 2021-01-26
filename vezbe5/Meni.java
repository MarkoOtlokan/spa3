import java.util.HashSet;
import java.util.Set;
import java.util.*;
import java.io.*;

public class Meni {
	private class Node {
		char key;
		String action;

		Set<Character> level;

		Node submenu;
		Node next;

		public Node() {
			level = new HashSet<>();
		}

		public Node(char key, String action) {
			this();
			this.key = key;
			this.action = action;
		}
	}

	Node root;
	Node currentPosition;
	int currentPositionLevel;

	public Meni(String fajl) throws Exception {
		root = new Node();
		currentPosition = root;
		currentPositionLevel = 0;
    BufferedReader buf = new BufferedReader(new FileReader(fajl));
    String t;
		while((t=buf.readLine()) != null) {
			String[] parts = t.split(",");
			add(parts[0], parts[1]);
		}
	}

	public void add(String path, String action) {
		if(path.length() == 0) {
			return;
		}
		try {
			add(root, path, action, 0);
		} catch (Exception e) {
			e.printStackTrace();
		//	System.out.println("Initialization error occurred. Quitting.");
			System.exit(1);
		}
	}

	private void add(Node node, String path, String action, int pos) throws Exception {
		if(node == null) {
			return;
		}

		if(pos == path.length()-1) {
			if(node.level.contains(path.charAt(pos))) {
				throw new Exception("Item already exists.");
			}
			node.level.add(path.charAt(pos));
			Node n = new Node(path.charAt(pos), action);
			if(node.submenu == null) {
				node.submenu = n;
			} else {
				Node s = node.submenu;
				while(s.next != null) {
					s = s.next;
				}
				s.next = n;
			}
			return;
		}

		char key = path.charAt(pos);
		Node n = node.submenu;
		while(n != null) {
			if(n.key == key) {
				add(n, path, action, pos+1);
				break;
			}

			n = n.next;
		}
	}

	public void print() {
		if(root.submenu == null) {
			return;
		}
		print(root.submenu, root.submenu.key, 1, false);
	}

	private void print(Node node, char key, int level, boolean singleLevel) {
		if(node == null) {
			return;
		}

		StringBuilder prefix = new StringBuilder();
		for(int i = 1; i < level; i++) {
			prefix.append("-");
		}
		if(prefix.length() > 0) {
			prefix.append(" ");
		}

		System.out.println(prefix + "("+key+") " + node.action);
		if(node.submenu != null) {
			print(node.submenu, node.submenu.key, level+1, singleLevel);
		}

		if(singleLevel) {
			if(level > 1 && node.next != null) {
				print(node.next, node.next.key, level, singleLevel);
			}
		} else {
			if(node.next != null) {
				print(node.next, node.next.key, level, singleLevel);
			}
		}
	}

	public void select(char c) {
		if(root.submenu == null) {
			return;
		}

		if(c == '.') {
			currentPosition = root;
			currentPositionLevel = 0;
			print();
		} else {
			Node pos = select(currentPosition, c);
			if(pos == null) {
				System.out.println("Child key does not exist. Try again.");
			} else {
				currentPosition = pos;
				currentPositionLevel++;
			}
		}

		if(currentPosition.submenu == null) {
			System.out.println("Action: "+currentPosition.action+" finished :-).");
			System.out.println("Restarting menu.");
			currentPosition = root;
			currentPositionLevel = 0;
		}

		if(currentPositionLevel > 0) {
			print(currentPosition, currentPosition.key, 1, true);
		} else {
			print();
		}
	}

	private Node select(Node node, char key) {
		if(node.submenu == null) {
			return null;
		}
		Node n = node.submenu;

		while(n != null) {
			if(n.key == key) {
				return n;
			}

			n = n.next;
		}

		return null;
	}


	public static void main(String[] args){
    try{
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Meni");
      Meni meni = new Meni("meni.txt");
      meni.print();

          while(true) {
              char c = reader.readLine().charAt(0);
              meni.select(c);
          }
    }catch (Exception e) {
      e.printStackTrace();
    }
	}
}
