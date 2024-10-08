package ru.job4j.ood.tdd;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Calendar;
import java.util.List;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class Cinema3DTest {
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(data -> true);
        assertThat(sessions).contains(session);
    }

    @Test
    public void whenBuyTicketOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyTicketOnOccupiedPlaceThenGetException() {
        Account first = new AccountCinema();
        Account second = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        cinema.buy(first, 1, 1, date);
        assertThatThrownBy(() -> cinema.buy(second, 1, 1, date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The place is occupied");
    }

    @Test
    public void whenBuyTicketOnInvalidDateThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar invalidDate = Calendar.getInstance();
        invalidDate.set(2025, Calendar.JANUARY, 1);
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, invalidDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid date");
    }

    @Test
    public void whenAddSessionWhichAlreadyExists() {
        Cinema cinema = new Cinema3D();
        Session first = new Session3D();
        Session second = new Session3D();
        cinema.add(first);
        assertThatThrownBy(() -> cinema.add(second))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The session already exists");
    }

}