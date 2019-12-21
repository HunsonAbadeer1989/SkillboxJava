/** Создайте класс, который будет представлять собой расчётный счёт в банке.
 * На этот расчётный счёт деньги можно положить, с него их можно снять,
 * и ещё можно посмотреть, сколько денег на счёте.
 * Создайте два класса-наследника - депозитарный расчётный счёт, с которого нельзя
 * снимать деньги в течение месяца после последнего внесения, и карточный счёт,
 * при снятии денег с которого будет взиматься комиссия 1%.
 */

public abstract class Account
{

    abstract void addMoney(double cash);
    abstract void withdrawMoney(double cash);
    abstract boolean transferTo(Account account, double money);
}
