package de.szut.onlinepoker.table;

import java.util.ArrayList;

public class TableTree {

	private TableNode root;
	
	public TableTree(){
		
	}
	
	public TableTree(Table t){
		root = new TableNode(t);
	}
	
	public boolean add(Table t){
		root = add(new TableNode(t), root);
		return true;
	}
	
	private TableNode add(TableNode t, TableNode r){

		if(r==null){
			r = t;
		}
		else if(t.getTable().getID()<r.getTable().getID()){
			r.setLeft(add(t, r.getLeft()));
			
			if(height(r.getLeft())-height(r.getRight())>=2){
				if(height(r.getLeft().getLeft())>height(r.getLeft().getRight())){
					r = rotateWithLeft(r);
					
				}
				else{
					r = doubleLeft(r);
				}
			}
			
			
		}
		else if(t.getTable().getID()>r.getTable().getID()){
			r.setRight(add(t, r.getRight()));
			
			if(height(r.getRight())-height(r.getLeft())>=2){
				
				if(height(r.getRight().getRight())-height(r.getRight().getLeft())>0){
					r = rotateWithRight(r);
				}
				else{
					r = doubleRight(r);
				}
			}
		}
		else{
			//doubled id
		}
		
		return r;
		
	}
	
	public void remove(Table t){
		remove(t.getID());
	}
	
	public void remove(int id){
		root = remove(id, root);
	}
	
	private TableNode remove(int id, TableNode t){
		
		
		if(t == null){
			return null;
		}
		
		if(id > t.getTable().getID()){
			t.setRight(remove(id, t.getRight()));
			if(height(t.getLeft())-height(t.getRight())>=2){
				
				if(height(t.getLeft().getLeft())>height(t.getLeft().getRight())){
					t = rotateWithLeft(t);
				}
				else{
					t = doubleLeft(t);
				}
			}
		}
		else if(id < t.getTable().getID()){
			t.setLeft(remove(id, t.getLeft()));
			if(height(t.getRight())-height(t.getLeft())>=2){
				
				if(height(t.getRight().getRight())>height(t.getRight().getLeft())){
					t = rotateWithRight(t);
				}
				else{
					t = doubleRight(t);
				}
			}
		}
		else{
			if(t.getLeft()!=null){
				TableNode n = findMax(t.getLeft());
				remove(n.getTable().getID(), t);
				t.setTable(n.getTable());
			}
			else{
				t = (t.getLeft() != null) ? t.getLeft() : t.getRight();
			}
			
		}
		return t;
	}
	
	public int height(){
		return height(root);
	}
	
	private int height(TableNode node){
		if(node == null){
			return 0;
		}
		int l = height(node.getLeft());
		int r = height(node.getRight());
		if(l>r){
			return l+1;
		}
		return r+1;
	}
	
	private TableNode findMax(TableNode root){
		if(root.getRight()==null){
			return root;
		}
		return findMax(root.getRight());
	}
	
	public Table getById(int id){
		TableNode n = getById(id, root);
		if(n==null){
			return null;
		}
		return n.getTable();
	}
	
	private TableNode getById(int id, TableNode r){
		if(r==null){
			return null;
		}
		
		if(id == r.getTable().getID()){
			return r;
		}
		else if(id>r.getTable().getID()){
			return getById(id, r.getRight());
		}else{
			return getById(id, r.getLeft());
		}
	}
	
	private TableNode rotateWithLeft(TableNode t){
		TableNode k = t.getLeft();
		t.setLeft(k.getRight());
		k.setRight(t);
		return k;
	}
	
	private TableNode rotateWithRight(TableNode t){
		TableNode k = t.getRight();
		t.setRight(k.getLeft());
		k.setLeft(t);
		return k;
	}
	
	private TableNode doubleRight(TableNode t){
		t.setRight(rotateWithLeft(t.getRight()));
		return rotateWithRight(t);
	}
	
	private TableNode doubleLeft(TableNode t){
		t.setLeft(rotateWithRight(t.getLeft()));
		return rotateWithLeft(t);
		
	}
	
	public int size(){
		return size(root);
	}
	
	private int size(TableNode root){

		if(root==null){
			return 0;
		}
		return size(root.getLeft())+size(root.getRight())+1;
	}
	
	
	/**
	 * 
	 * @return a sorted arry of all tables
	 */
	public Table[] inorder(){
		int size = size();
		Table[] toret = new Table[size];
		ArrayList<TableNode> temp = new ArrayList<TableNode>(size);
		temp = inorder(root, temp);
		for(int i = 0; i<size; i++){
			toret[i] = temp.get(i).getTable();
		}
		return toret;
	}
	
	private ArrayList<TableNode> inorder(TableNode root, ArrayList<TableNode> toSave){
		TableNode left = root.getLeft();
		TableNode right = root.getRight();
		
		if(left!=null){
			toSave = inorder(left, toSave);
		}
		toSave.add(root);
		if(right!=null){
			toSave = inorder(right, toSave);
		}
		return toSave;
	}
	
	
	
	
	
}