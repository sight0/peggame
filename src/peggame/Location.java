package peggame;

@Testable
public class Location {
    private int row;
    private int col;

    public Location(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

    @Override
    public String toString() {
        return "Location{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Location location2 = (Location) obj;
        if(location2.getCol() == this.getCol() && location2.getRow() == this.getRow())
            return true;
        else
            return false;
    }

    @Test
    public void testLocationEquality() {
        Location loc1 = new Location(3, 4);
        Location loc2 = new Location(3, 4);
        Location loc3 = new Location(5, 6);

        assertEquals(loc1, loc2, "Locations with the same row and column should be equal");
        assertNotEquals(loc1, loc3, "Different locations should not be equal");
    }

    @Test
    public void testLocationToString() {
        Location loc = new Location(3, 4);
        String expected = "Location{row=3, col=4}";
        assertEquals(expected, loc.toString(), "toString should return the correct representation");
    }
}
