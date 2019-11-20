public class TextChanger
{
    public static void main(String[] args) {
        String text = "Some days, when he takes his labrador, Buddy, for a walk, Brian Cooper strolls five minutes from his home to the banks of the River Dee and watches it flow towards Aberdeen. \n" +
                "\n" +
                "Brian grew up there in a two-bedroom tenement flat in Froghall, a council estate near the city centre. It was a close-knit place - his grandmother lived nearby, and an aunt and uncle too. Brian’s father was a bricklayer by trade, his mother variously a shop assistant, barmaid and carer. After leaving school at 16, Brian signed up for an apprenticeship with the Post Office. \n" +
                "\n" +
                "Then, at 21, Brian landed a job as an office boy with one of the energy companies that proliferated in the city after the discovery of North Sea oil - his father was among the men who went to work offshore. It meant a pay cut for Brian at first, but he knew it was an opportunity. He rose quickly through the ranks, eventually becoming a project manager before moving into business development and sales.\n" +
                "\n" +
                "Brian with his dog\n" +
                "\n" +
                "Soon, Brian and his wife were able to buy an imposing detached house in the affluent suburb of Milltimber. Though it lies within the city boundaries, it feels like a village. In the summer, families have picnics by the banks of the river. \n" +
                "\n" +
                "Brian, 37, isn’t the sort to put posters in the window of his home at election time. \"I wouldn’t say I was a political animal,” he says. He’s well aware he doesn’t have all the answers. “I’m probably more of a distant spectator trying to form my own view.”\n" +
                "\n" +
                "And so in 2014, as the referendum on Scottish independence approached, Brian carefully studied the arguments for and against. “I felt there were positives as an independent country,” he says. With its natural resources and human capital, he was confident Scotland could succeed on its own. “Scottish people are pretty resilient, we are pretty welcoming, we welcome others into the country - I always felt we would build something.” \n" +
                "\n" +
                "\n" +
                "Brian's dog\n" +
                "That September, the morning after the No campaign’s victory was declared, Brian was disappointed. But he quickly picked himself up, accepting that a decision had been made, although he still believed that, in the long term, Scotland should be a nation state in its own right. \n" +
                "\n" +
                "Then, nearly two years later, there was another referendum to consider - this time on the UK’s membership of the European Union. He hadn’t thought much about the issue before, but it seemed the original aims of the European project - to bring peace and stability to the continent - had been met. The crisis in the Eurozone made him wonder what the point of it was in the current era. \n" +
                "\n" +
                "“You start thinking, ‘So it started out as something, but is it still serving that purpose to this day? The world we live in now, is that where we were when this bloc was formed?’ I think we live in a completely different time.” \n" +
                "\n" +
                "On the morning of 24 June 2016, he woke to find that while Scotland had voted heavily to Remain, his side - Leave - had narrowly won across the UK as whole. He assumed the politicians at Westminster would do as they had been instructed and make Brexit happen. But as the years went on and they failed to do so, he grew increasingly frustrated. \n" +
                "\n" +
                "“For me, I just think everyone now wants it done,” he says. He’d rather Brexit was completed before another independence referendum. “You need to take care of Brexit first, understand where it’s going to lead to.“ " ;

        String[] newText = text.replaceAll("\\W[\\W, \\s]\\W*", " ").split(" ");

        for (String a: newText)
        {
            System.out.println(a);
        }
    }
}
