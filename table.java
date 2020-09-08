package com.toddperkins;

import java.awt.EventQueue;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.JScrollPane;

public class table {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					table window = new table();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	class TableData extends AbstractTableModel {

		//class field
		int[][] dataArray;
		private static final long serialVersionUID = 4877111344044700307L;
		
		//initialize field
		public TableData() {
//			dataArray[0] = new int[] {1, 2, 3};
//			dataArray[1] = new int[] {4, 5, 6};
//			dataArray[2] = new int[] {7, 8, 9};
			//loadFile("data.csv");
			loadFile("file.csv");
		
		}
		
		//load external files
		void loadFile(String fileName) {
			Path file = FileSystems.getDefault().getPath("src", fileName);
			try {
				List<String> lines = Files.readAllLines(file);
				for (int i = 0; i < lines.size(); i++) {
					String line = lines.get(i);
					String[] lineArray = line.split(", ");
					if (dataArray == null) {
						dataArray = new int[lines.size()][lineArray.length];
					}
					for (int j = 0; j < lineArray.length; j++) {
						int parsedInt = Integer.parseInt(lineArray[j]);
						dataArray[i][j] = parsedInt;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return dataArray[0].length;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return dataArray.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int colIndex) {
			// TODO Auto-generated method stub
			return dataArray[rowIndex][colIndex];
		}
		
	}

	/**
	 * Create the application.
	 */
	public table() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 641, 476);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(72, 54, 485, 51);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		TableData data = new TableData();
		table.setModel(data);
	}
}
