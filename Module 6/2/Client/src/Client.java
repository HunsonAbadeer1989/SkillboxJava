/** Реализуйте классы, представляющие клиентов банка: абстрактный класс Client,
 * а также классы для физических лиц, юридических лиц и индивидуальных предпринимателей.
 * У каждого клиента есть расчётный счёт (число), который можно пополнять, с которого можно снимать,
 * и баланс на котором можно смотреть.
 * Реализовать методы таким образом, чтобы:
 * у физических лиц пополнение и снятие происходило без комиссии,
 * у юридических лиц — снятие с комиссией 1%,
 * а у ИП — пополнение с комиссией 1%, если сумма меньше 1000 рублей,
 * и 0,5%, если сумма больше либо равна 1000 рублей. */
public abstract class Client
{
    private double fund;

    public double getFund() {
        return fund;
    }

    public void setFund(double fund) {
        this.fund = fund;
    }

    public Client(double fund) {
        this.fund = fund;
    }

    public void addMoney(double cash) {
        if ( cash < 0) {
            System.out.println("Wrong amount!");
        }
        else {
            setFund(getFund() + cash);
        }
    }

    void withdrawMoney(double cash){
        setFund(getFund() - cash);
    }

    double balance(){
        return getFund();
    }
}
