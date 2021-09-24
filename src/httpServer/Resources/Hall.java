package httpServer.Resources;

public class Hall {
    private enum Rows {
        A(0), B(1), C(2), D(3), E(4), F(5), G(6), H(7), I(8), J(9);
        private final int row;
        Rows(int num) {
            this.row = num;
        }
    }
    private String hallName;
    private class WrongSeatNameException extends RuntimeException {
        WrongSeatNameException() {
            super("Wrong seat name\n");
        }
    }
    private class SeatNotAvailable extends RuntimeException {
        SeatNotAvailable() {
            super("Seat is taken\n");
        }
    }
    private final Integer[][] seats = new Integer[10][10];
    private int vacantSeats;
    public Hall(String hallName) {
        for (Integer[] rows : seats) {
            for (Integer seat : rows) {
                seat = null;
            }
        }
        vacantSeats = 100;
        this.hallName = hallName;
    }
    public void chooseSeat(String seatNum) {
        int row, column;
        try {
            row = Rows.valueOf(seatNum.substring(0, 1)).row;
            column = Integer.parseInt(seatNum.substring(1));
            if (column < 1 || column > 10) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException exception) {
            throw new WrongSeatNameException();
        }
        if (seats[row][column - 1] != null) {
            throw new SeatNotAvailable();
        } else {
            seats[row][column - 1] = 1;
            --vacantSeats;
            //buyingTicket
        }
    }
    public String printSeatsToString() {
        StringBuilder seatsToStr = new StringBuilder();
        seatsToStr.append("   ");
        for (int i = 0; i < 10; i++) {
            seatsToStr.append(String.format("%2d ", i + 1));
        }
        seatsToStr.append("\n");
        for (int i = 0; i < 10; i++) {
            seatsToStr.append(String.format("%2s ", Rows.values()[i].toString()));
            for (int j = 0; j < 10; j++) {
                char ch;
                if (seats[i][j] == null) {
                    ch = 'O';
                } else {
                    ch = 'X';
                }
                seatsToStr.append(String.format("%2c ", ch));
            }
            seatsToStr.append("\n");
        }
        return seatsToStr.toString();
    }
    public String getHallName() {
        return hallName;
    }
    public int getVacantSeats() {
        return vacantSeats;
    }

    //this method must clean all bought seats after session
    public void vacateTheHall() {

    }
}
