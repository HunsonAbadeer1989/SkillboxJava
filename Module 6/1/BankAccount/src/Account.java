/** Создайте класс, который будет представлять собой расчётный счёт в банке.
 * На этот расчётный счёт деньги можно положить, с него их можно снять,
 * и ещё можно посмотреть, сколько денег на счёте.
 * Создайте два класса-наследника - депозитарный расчётный счёт, с которого нельзя
 * снимать деньги в течение месяца после последнего внесения, и карточный счёт,
 * при снятии денег с которого будет взиматься комиссия 1%.
 */

public abstract class Account
{
    Account(double fund){
        setFund(fund);
    }
    protected double fund;

    protected double getFund() {
        return fund;
    }

    protected void setFund(double fund) {
        this.fund = fund;
    }

    abstract void addMoney(double cash);
    abstract boolean withdrawMoney(double cash);
    boolean transferTo(Account account, double money){
        if (money < 0) {
            return false;
        }
        if (withdrawMoney(money)) {
            account.addMoney(money);
            return true;
        } else {
            return false;
        }
    }
}
