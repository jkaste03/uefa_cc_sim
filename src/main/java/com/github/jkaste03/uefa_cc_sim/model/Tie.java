package com.github.jkaste03.uefa_cc_sim.model;

/**
 * Abstract representation of a tie between two clubs.
 * <p>
 * This class encapsulates the common behavior for matches in UEFA contexts,
 * such as participating clubs, score calculations, and determining the winner.
 * Implementing classes should specify the details for the specific tie
 * format.
 */
public abstract class Tie extends ClubSlot {
    protected ClubSlot clubSlot1;
    protected ClubSlot clubSlot2;
    protected int club1Goals = -1;
    protected int club2Goals = -1;
    protected ClubSlot winner;

    /**
     * Constructs a new tie with the specified club slots.
     *
     * @param clubSlot1 the first club slot participating in the tie.
     * @param clubSlot2 the second club slot participating in the tie.
     */
    public Tie(ClubSlot clubSlot1, ClubSlot clubSlot2) {
        this.clubSlot1 = clubSlot1;
        this.clubSlot2 = clubSlot2;
    }

    public ClubSlot getClubSlot1() {
        return clubSlot1;
    }

    public void setClubSlot1(ClubSlot club1) {
        this.clubSlot1 = club1;
    }

    public ClubSlot getClubSlot2() {
        return clubSlot2;
    }

    public void setClubSlot2(ClubSlot club2) {
        this.clubSlot2 = club2;
    }

    public ClubSlot getWinner() {
        return winner;
    }

    public ClubSlot getLoser() {
        return winner == clubSlot1 ? clubSlot2 : clubSlot1;
    }

    /**
     * Simulates the match.
     * <p>
     * Implementing methods should perform the match, update the results,
     * and set the winner based on the match outcome.
     */
    public abstract void play();

    /**
     * Generates a random scoreline for the match.
     * <p>
     * The method simulates goal scoring by generating random integers between 0 and
     * 3 for each club.
     *
     * @return an array where the first element is the goals for club 1 and the
     *         second element is the goals for club 2.
     */
    protected int[] genScoreline() {
        int club1Goals = (int) (Math.random() * 4);
        int club2Goals = (int) (Math.random() * 4);
        return new int[] { club1Goals, club2Goals };
    }

    /**
     * Updates the participating club slots in the tie if they are wrappers.
     * <p>
     * If a club slot is an instance of a DoubleLeggedTieWrapper,
     * it retrieves the appropriate underlying club slot (tie winner or loser) for
     * further use.
     */
    public void updateClubSlotsIfTie() {
        if (clubSlot1 instanceof DoubleLeggedTieWrapper) {
            clubSlot1 = ((DoubleLeggedTieWrapper) clubSlot1).getCorrectClub();
        }
        if (clubSlot2 instanceof DoubleLeggedTieWrapper) {
            clubSlot2 = ((DoubleLeggedTieWrapper) clubSlot2).getCorrectClub();
        }
    }

    @Override
    public String toString() {
        return "Tie [clubSlot1=" + clubSlot1 + ", clubSlot2=" + clubSlot2 + ", winner=" + winner.getName()
                + ", club1Goals=" + club1Goals + ", club2Goals=" + club2Goals + "]";
    }
}