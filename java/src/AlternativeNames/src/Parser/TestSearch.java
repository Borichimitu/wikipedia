package Parser;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit Test na hladanie vyrazov vo vyparsovanom subore.
 * @author Spred
 *
 */
public class TestSearch {
	main mainclass = new main();
	JTextField searchExpression;
	JButton btnSearch;
	JTextArea ResultData;
	JComboBox<String> TagCombo;
	@Before 
	public void init(){
		searchExpression = new JTextField();
		mainclass.setGu(new GUI());
		mainclass.getGu().createGUI();
		searchExpression = mainclass.getGu().searchExpression;
		btnSearch = mainclass.getGu().btnSearch;
		ResultData= mainclass.getGu().ResultData;
		TagCombo = mainclass.getGu().TagCombo;
	}
	@Test
	public void TestNoneresult() {
		searchExpression.setText("peknydenprajem");
		btnSearch.doClick();
		String result = ResultData.getText();
		String ExpectedResult = "Found 0 hits.";
		Assert.assertTrue(result.contains(ExpectedResult));
	}
	@Test
	public void Test1result() {
		searchExpression.setText("blindness");
		btnSearch.doClick();
		String result = ResultData.getText();
		String ExpectedResult = "Found 1 hits.";
		Assert.assertTrue(result.contains(ExpectedResult));
	}
	@Test
	public void TestMoreresult() {
		searchExpression.setText("ford");
		btnSearch.doClick();
		String result = ResultData.getText();
		String ExpectedResult = "Found 0 hits.";
		String ExpectedResult2 = "Found 1 hits.";
		Assert.assertTrue(!result.contains(ExpectedResult) && !result.contains(ExpectedResult2));
	}

	@Test
	public void TestAkaresult() {
		searchExpression.setText("\"Ford Model N\"");
		btnSearch.doClick();
		String result = ResultData.getText();
		String ExpectedResult = "aka: Ford Model R Ford Model S";
		Assert.assertTrue(result.contains(ExpectedResult));
	}
	@Test
	public void TestMoreAkaTextresult() {
		searchExpression.setText("blindness");
		btnSearch.doClick();
		String result = ResultData.getText();
		String [] resultSubstring = result.split("Title");
		Assert.assertTrue(resultSubstring.length==3);
	}
	@Test
	public void TestSearchAkaText() {
		searchExpression.setText("Docklands");
		TagCombo.setSelectedIndex(2); // select akaTextTitle
		btnSearch.doClick();
		String result = ResultData.getText();
		String ExpectedResult = "Aarhus Docklands";
		Assert.assertTrue(result.contains(ExpectedResult));
	}
}

