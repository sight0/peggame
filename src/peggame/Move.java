package peggame;

@Testable
public class Move {
    private Location from;
    private Location to;

    public Move(Location from, Location to){
        this.from = from;
        this.to = to;
    }

    public Location getFrom(){
        return this.from;
    }

    public Location getTo(){
        return this.to;
    }

    @Override
    public String toString() {
        return "Move{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }

    @Override
    public boolean equals(Object o){
        Move move2 = (Move) o;
        if(move2.getFrom().equals(this.getFrom()) && move2.getTo().equals(this.getTo()))
            return true;
        else
            return false;
    }

    @Test
    public void testMoveEquality() {
        Location loc1 = new Location(1, 1);
        Location loc2 = new Location(2, 2);
        Move move1 = new Move(loc1, loc2);
        Move move2 = new Move(loc1, loc2);

        assertEquals(move1, move2, "Two moves with the same locations should be equal");
    }

    @Test
    public void testMoveToString() {
        Location loc1 = new Location(1, 1);
        Location loc2 = new Location(2, 2);
        Move move = new Move(loc1, loc2);

        String expected = "Move{from=Location{row=1, col=1}, to=Location{row=2, col=2}}";
        assertEquals(expected, move.toString(), "toString should return the correct representation");
    }
}
