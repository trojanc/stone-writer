package coza.trojanc.receipt.format;

import coza.trojanc.receipt.shared.LineWrap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Charl Thiem
 */
public class PrintBuilderTestCases {


	private List<TestInstance> tests = new ArrayList<>();

	/**
	 * Getter for property 'tests'.
	 *
	 * @return Value for property 'tests'.
	 */
	public List<TestInstance> getTests() {
		return tests;
	}

	/**
	 * Setter for property 'tests'.
	 *
	 * @param tests Value to set for property 'tests'.
	 */
	public void setTests(List<TestInstance> tests) {
		this.tests = tests;
	}

	public static class TestInstance{
		private String text;
		private int index;
		private int lineWidth;
		private LineWrap lineWrap;
		private String resultLeft;
		private String resultRight;
		private String resultCenter;

		public TestInstance(){
		}

		/**
		 * Getter for property 'lineWidth'.
		 *
		 * @return Value for property 'lineWidth'.
		 */
		public int getLineWidth() {
			return lineWidth;
		}

		/**
		 * Setter for property 'lineWidth'.
		 *
		 * @param lineWidth Value to set for property 'lineWidth'.
		 */
		public void setLineWidth(int lineWidth) {
			this.lineWidth = lineWidth;
		}

		/**
		 * Getter for property 'text'.
		 *
		 * @return Value for property 'text'.
		 */
		public String getText() {
			return text;
		}

		/**
		 * Setter for property 'text'.
		 *
		 * @param text Value to set for property 'text'.
		 */
		public void setText(String text) {
			this.text = text;
		}

		/**
		 * Getter for property 'lineWrap'.
		 *
		 * @return Value for property 'lineWrap'.
		 */
		public LineWrap getLineWrap() {
			return lineWrap;
		}

		/**
		 * Setter for property 'lineWrap'.
		 *
		 * @param lineWrap Value to set for property 'lineWrap'.
		 */
		public void setLineWrap(LineWrap lineWrap) {
			this.lineWrap = lineWrap;
		}

		/**
		 * Setter for property 'index'.
		 *
		 * @param index Value to set for property 'index'.
		 */
		public void setIndex(int index) {
			this.index = index;
		}

		/**
		 * Setter for property 'resultLeft'.
		 *
		 * @param resultLeft Value to set for property 'resultLeft'.
		 */
		public void setResultLeft(String resultLeft) {
			this.resultLeft = resultLeft;
		}


		/**
		 * Setter for property 'resultCenter'.
		 *
		 * @param resultCenter Value to set for property 'resultCenter'.
		 */
		public void setResultCenter(String resultCenter) {
			this.resultCenter = resultCenter;
		}

		/**
		 * Getter for property 'index'.
		 *
		 * @return Value for property 'index'.
		 */
		public int getIndex() {
			return index;
		}

		/**
		 * Getter for property 'resultLeft'.
		 *
		 * @return Value for property 'resultLeft'.
		 */
		public String getResultLeft() {
			return resultLeft;
		}

		/**
		 * Getter for property 'resultRight'.
		 *
		 * @return Value for property 'resultRight'.
		 */
		public String getResultRight() {
			return resultRight;
		}

		/**
		 * Setter for property 'resultRight'.
		 *
		 * @param resultRight Value to set for property 'resultRight'.
		 */
		public void setResultRight(String resultRight) {
			this.resultRight = resultRight;
		}

		/**
		 * Getter for property 'resultCenter'.
		 *
		 * @return Value for property 'resultCenter'.
		 */
		public String getResultCenter() {
			return resultCenter;
		}
	}

}
