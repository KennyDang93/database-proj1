package db61b;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
public class BasicTests {
    @Test
    public void testRow() {
        Row r = new Row(new String[] {"so", "excited", "right", "now."});
        Row x = new Row(new String[] {"so", "excited", "right", "now."});
        assertEquals(4, r.size());
        assertEquals(true, r.equals(x));
    }
    @Test
    public void testGetRow() {
        Row r = new Row(new String[] {"so", "excited", "right", "now."});
        assertEquals("so", r.get(0));
    }
    @Test
    public void testGetColumn() {
        Table t = new Table(new String[] {"this", "is", "a", "new", "table"});
        assertEquals(5, t.columns());
        assertEquals("table", t.getTitle(4));

    }
    @Test
    public void testTable() {
        Table t = new Table(new String[] {"SID", "LastName", "FirstName",
            "SemesterEnter", "YearEnter", "Major"});
        Row jason = new Row(new String[] {"101", "Knowles", "Jason",
            "F", "2003", "EECS"});
        Row valerie = new Row(new String[] {"102", "Chan", "Valerie",
            "S", "2003", "Math"});
        Row xavier = new Row(new String[] {"103", "Xavier",
            "Jonathan", "S", "2004", "LSUnd"});
        t.add(jason);
        t.add(valerie);
        t.add(xavier);
        assertEquals(false, t.add(jason));

    }
    @Test
    public void testDatabase() {
        Table t = new Table(new String[] {"SID", "LastName", "FirstName",
            "SemesterEnter", "YearEnter", "Major"});
        Row jason = new Row(new String[] {"101", "Knowles", "Jason",
            "F", "2003", "EECS"});
        Row valerie = new Row(new String[] {"102", "Chan",
            "Valerie", "S", "2003", "Math"});
        Row xavier = new Row(new String[] {"103", "Xavier", "Jonathan",
            "S", "2004", "LSUnd"});
        t.add(jason);
        t.add(valerie);
        t.add(xavier);
        Database d = new Database();
        assertEquals(null, d.get("scsdc"));
        d.put("students", t);
        assertEquals(t, d.get("students"));
    }
    @Test public void testReadTable() {
        Table t = new Table(new String[] {"SID", "LastName", "FirstName",
            "SemesterEnter", "YearEnter", "Major"});
        Row jason = new Row(new String[] {"101", "Knowles", "Jason",
            "F", "2003", "EECS"});
        Row valerie = new Row(new String[] {"102", "Chan", "Valerie",
            "S", "2003", "Math"});
        Row xavier = new Row(new String[] {"103", "Xavier",
            "Jonathan", "S", "2004", "LSUnd"});
        t.add(jason);
        t.add(valerie);
        t.add(xavier);
        t.writeTable("morestudents");
    }
    @Test
    public void testTableSelect() {
        Table students = Table.readTable("testing/students");
        List<String> listStr = new ArrayList<String>();
        listStr.add("Lastname");
        listStr.add("Firstname");
        List<Condition> conditions = new ArrayList<Condition>();
        Table newTable = students.select(listStr, conditions);
        newTable.writeTable("names");
        List<String> anotherListStr = new ArrayList<String>();
        anotherListStr.add("Major");
        Table newTable2 = students.select(anotherListStr, conditions);
        newTable2.writeTable("department");
    }
    @Test
    public void testConditions() {
        Table t = new Table(new String[] {"SID", "LastName", "FirstName",
            "SemesterEnter", "YearEnter", "Major"});
        Row jason = new Row(new String[] {"101", "Knowles", "Jason",
            "F", "2003", "EECS"});
        Row valerie = new Row(new String[] {"102", "Chan", "Valerie",
            "S", "2003", "Math"});
        Row xavier = new Row(new String[] {"103", "Xavier", "Jonathan",
            "S", "2004", "LSUnd"});
        t.add(jason);
        t.add(valerie);
        t.add(xavier);
    }
    @Test
    public void testNewRowConstructor() {
        List<Column> columns = new ArrayList<Column>();
        Table t = new Table(new String[] {"SID", "LastName", "FirstName",
            "SemesterEnter", "YearEnter", "Major"});
        Row jason = new Row(new String[] {"101", "Knowles", "Jason",
            "F", "2003", "EECS"});
        Row valerie = new Row(new String[] {"102", "Chan", "Valerie",
            "S", "2003", "Math"});
        Row xavier = new Row(new String[] {"103", "Xavier", "Jonathan",
            "S", "2004", "LSUnd"});
        t.add(jason);
        t.add(valerie);
        t.add(xavier);
        Table t2 = new Table(new String[] {"SID", "LastName", "FirstName",
            "SemesterEnter", "YearEnter", "Major"});
        Row thomas = new Row(new String[] {"104", "Armstrong", "Thomas",
            "F", "2003", "EECS"});
        Row shana = new Row(new String[] {"105", "Brown", "Shana",
            "S", "2003", "Math"});
        Row yangfan = new Row(new String[] {"106", "Chan", "Yangfan",
            "S", "2004", "LSUnd"});
        t2.add(thomas);
        t2.add(shana);
        t2.add(xavier);
        Row r1 = new Row(new String[]{"107", "Dannng!", "Kenny",
            "S", "2010", "CS/Philosphy"});
        Row r2 = new Row(new String[]{"108", "Lam", "Wilson",
            "S", "2013", "Philosphy/CS"});
        Row r3 = new Row(new String[]{"108", "Lin", "Ant",
            "S", "2013", "CS"});
        Row r4 = new Row(new String[]{"109", "Casanova", "James",
            "F", "2013", "Philosphy"});

        assertEquals("Dannng!", r1.get(1));
        assertEquals("Wilson", r2.get(2));
        assertEquals("S", r3.get(3));
        assertEquals("Philosphy", r4.get(5));
    }

    @Test
    public void testEquijoin() {
        List<Column> cols1 = new ArrayList<Column>();
        List<Column> cols2 = new ArrayList<Column>();
        Table t = new Table(new String[] {"SID", "LastName", "FirstName",
            "SemesterEnter", "YearEnter", "Major"});
        Row jason = new Row(new String[] {"101", "Knowles", "Jason",
            "F", "2003", "EECS"});
        Row valerie = new Row(new String[] {"102", "Chan", "Valerie",
            "S", "2003", "Math"});
        Row xavier = new Row(new String[] {"103", "Xavier", "Jonathan",
            "S", "2004", "LSUnd"});
        t.add(jason);
        t.add(valerie);
        t.add(xavier);
        Row r1 = new Row(new String[]{"107", "Dannng!", "Kenny",
            "S", "2010", "CS/Philosphy"});
        Row r2 = new Row(new String[]{"108", "Lam", "Wilson",
            "S", "2013", "Philosphy/CS"});
        Row r3 = new Row(new String[]{"108", "Lin", "Ant",
            "S", "2013", "CS"});
        Row r4 = new Row(new String[]{"109", "Casanova", "James",
            "F", "2013", "Philosphy"});

        Table t2 = new Table(new String[] {"SID", "LastName", "FirstName",
            "SemesterEnter", "YearEnter", "Major"});
        Row thomas = new Row(new String[] {"104", "Armstrong", "Thomas",
            "F", "2003", "EECS"});
        Row shana = new Row(new String[] {"105", "Brown", "Shana",
            "S", "2003", "Math"});
        Row yangfan = new Row(new String[] {"106", "Chan", "Yangfan",
            "S", "2004", "LSUnd"});
        t2.add(thomas);
        t2.add(shana);
        t2.add(xavier);
        Column c1 = new Column("SID", t, t2);
        Column c2 = new Column("SID", t, t2);
        cols1.add(c1);
        cols2.add(c2);
    }
    @Test
    public void conditionsTest() {
        List<Condition> conditions = new ArrayList<Condition>();
        List<String> columnsNames = new ArrayList<String>();
        Table t = new Table(new String[] {"SID", "LastName", "FirstName",
            "SemesterEnter", "YearEnter", "Major"});
        Row jason = new Row(new String[] {"101", "Knowles", "Jason",
            "F", "2003", "EECS"});
        Row valerie = new Row(new String[] {"102", "Chan", "Valerie",
            "S", "2003", "Math"});
        Row xavier = new Row(new String[] {"103", "Xavier", "Jonathan",
            "S", "2004", "LSUnd"});
        t.add(jason);
        t.add(valerie);
        t.add(xavier);
        conditions.add(new Condition(new Column("SID", t), "=" , "103"));
        columnsNames.add("SID");
        columnsNames.add("FirstName");
        columnsNames.add("Major");
        Table newTable = t.select(columnsNames, conditions);
    }

    @Test
    public void tableSelect() {
        List<Condition> conditions = new ArrayList<Condition>();
        List<String> columnsNames = new ArrayList<String>();
        Row r1 = new Row(new String[]{"107", "Dannng!", "Kenny",
            "S", "2010", "CS/Philosphy"});
        Row r2 = new Row(new String[]{"108", "Lam", "Wilson",
            "S", "2013", "Philosphy/CS"});
        Row r3 = new Row(new String[]{"110", "Lin", "Ant",
            "S", "2013", "CS"});
        Row r4 = new Row(new String[]{"109", "Casanova", "James",
            "F", "2013", "Philosphy"});
        columnsNames.add("SID");
        columnsNames.add("FirstName");
        columnsNames.add("Major");
        Table t = new Table(columnsNames);
        t.add(r1);
        t.add(r2);
        t.add(r3);
        t.add(r4);
        conditions.add(new Condition(new Column("SID", t), "<" , "110"));
        Table newTable = t.select(columnsNames, conditions);

    }

    @Test
    public void twoTableSelect() {
        List<Condition> conditions = new ArrayList<Condition>();
        List<String> columnsNames = new ArrayList<String>();
        Row r1 = new Row(new String[]{"107", "Dannng!", "Kenny",
            "S", "2010", "CS/Philosphy"});
        Row r2 = new Row(new String[]{"108", "Lam", "Wilson",
            "S", "2013", "Philosphy/CS"});
        Row r3 = new Row(new String[]{"110", "Lin", "Ant",
            "S", "2013", "CS"});
        Row r4 = new Row(new String[]{"109", "Casanova", "James",
            "F", "2013", "Philosphy"});
        columnsNames.add("SID");
        columnsNames.add("FirstName");
        columnsNames.add("Major");
        Table t = new Table(columnsNames);
        t.add(r1);
        t.add(r2);
        t.add(r3);
        t.add(r4);
        Table t2 = new Table(columnsNames);
        Row jason = new Row(new String[] {"101", "Knowles", "Jason",
            "F", "2003", "EECS"});
        Row valerie = new Row(new String[] {"102", "Chan", "Valerie",
            "S", "2003", "Math"});
        Row xavier = new Row(new String[] {"103", "Xavier", "Jonathan",
            "S", "2004", "LSUnd"});
        t2.add(jason);
        t2.add(valerie);
        t2.add(xavier);
        conditions.add(new Condition(new Column("SID", t, t2), ">" , "105"));
        Table newTable = t.select(t2 , columnsNames, conditions);
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(BasicTests.class));
    }
}