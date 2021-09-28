package httpServer.Resources;

public class SessionTickets {
    private final Integer[][] seats;
    private final int size;
    private Integer tickets;

    public SessionTickets(int size) {
        size = Math.min(size, 10);
        seats = new Integer[size][size];
        for (Integer[] rows : seats) {
            for (Integer seat : rows) {
                seat = null;
            }
        }
        this.size = size;
        tickets = size * size;
    }

    public void chooseSeat(String seatNum) {
        int row, column;
        try {
            row = Rows.valueOf(seatNum.toUpperCase().substring(0, 1)).row;
            column = Integer.parseInt(seatNum.substring(1));
            if (column < 1 || column > size) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException exception) {
            throw new WrongSeatNameException();
        }
        if (seats[row][column - 1] != null) {
            throw new SeatNotAvailable();
        } else {
            seats[row][column - 1] = 1;
            tickets--;
        }
    }

    public String printSeatsToString() {
        StringBuilder seatsToStr = new StringBuilder();
        seatsToStr.append("   ");
        for (int i = 0; i < size; i++) {
            seatsToStr.append(String.format("%2d ", i + 1));
        }
        seatsToStr.append("\n");
        for (int i = 0; i < size; i++) {
            seatsToStr.append(String.format("%2s ", Rows.values()[i].toString()));
            for (int j = 0; j < size; j++) {
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

    public int getTickets() {
        return tickets;
    }

    private enum Rows {
        A(0), B(1), C(2), D(3), E(4),
        F(5), G(6), H(7), I(8), J(9);
        private final int row;

        Rows(int num) {
            this.row = num;
        }
    }

    private static class WrongSeatNameException extends RuntimeException {
        WrongSeatNameException() {
            super("Wrong seat name\n");
        }
    }

    private static class SeatNotAvailable extends RuntimeException {
        SeatNotAvailable() {
            super("Seat is taken\n");
        }
    }

}
