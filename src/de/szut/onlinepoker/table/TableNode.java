package de.szut.onlinepoker.table;

public class TableNode {

	private Table t;
	private TableNode l = null;
	private TableNode r = null;
	
	public Table getTable() {
		return t;
	}
	public void setTable(Table t) {
		this.t = t;
	}
	public TableNode getLeft() {
		return l;
	}
	public void setLeft(TableNode l) {
		this.l = l;
	}
	public TableNode getRight() {
		return r;
	}
	public void setRight(TableNode r) {
		this.r = r;
	}
	
	public TableNode(Table t){
		this.t=t;
	}
	
}
