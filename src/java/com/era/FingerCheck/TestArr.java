package com.era.FingerCheck;

import java.sql.*;

public class TestArr implements SQLData {

    public String sql_type;
    public int attrOne;
    
    //public int    attrTwo;

    public TestArr() {
    }

    public TestArr(String sql_type, int attrOne)  //, int attrTwo
    { 
        this.sql_type = sql_type;
        this.attrOne = attrOne;
    }

    // define a get method to return the SQL type of the object
    public String getSQLTypeName() throws SQLException {
        return sql_type;
    }

    // define the required readSQL() method 
    public void readSQL(SQLInput stream, String typeName)
            throws SQLException {
        sql_type = typeName;
        attrOne = stream.readInt();
        //attrOne = stream.readString();
        
    }
    // define the required writeSQL() method 

    public void writeSQL(SQLOutput stream)
            throws SQLException {
        stream.writeInt(attrOne);
        //stream.writeString(attrOne);
    }
}

