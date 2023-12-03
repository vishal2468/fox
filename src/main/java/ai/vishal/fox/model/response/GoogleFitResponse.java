package ai.vishal.fox.model.response;

import java.util.ArrayList;

public class GoogleFitResponse {
    public ArrayList<Bucket> bucket;
}
class Bucket{
    public String startTimeMillis;
    public String endTimeMillis;
    public ArrayList<Dataset> dataset;
}

class Dataset{
    public String dataSourceId;
    public ArrayList<Point> point;
}

class Point{
    public String startTimeNanos;
    public String endTimeNanos;
    public String dataTypeName;
    public String originDataSourceId;
    public ArrayList<Value> value;
}

class Value{
    public double fpVal;
    public ArrayList<Object> mapVal;
}