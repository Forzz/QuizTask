package com.forzz.quiztask.common

import com.forzz.quiztask.model.QuizElement

object Constants {

    val baseImagesPath: String = "res/drawable/"

    val questionList: List<QuizElement> = listOf(
        QuizElement("Mr and Mrs Dursley, of number four, Privet Drive, were proud to say that they were perfectly normal, thank you very much.", "harrypotter", false),
        QuizElement("Here is Edward Bear, coming downstairs now, bump, bump, bump, on the back of his head, behind Christopher Robin.", "winniepooh", false),
        QuizElement("The Time Traveller (for so it will be convenient to speak of him) was expounding a recondite matter to us.", "timemachine", false),
        QuizElement("The old lady pulled her spectacles down and looked over them about the room; then she put them up and looked out under them.", "tomsawyer", false),
        QuizElement("Alice was beginning to get very tired of sitting by her sister on the bank, and of having nothing to do", "alice", false),
        QuizElement("In my younger and more vulnerable years my father gave me some advice that I’ve been turning over in my mind ever since.", "gatsby", false),
        QuizElement("Well, Prince, so Genoa and Lucca are now just family estates of the Buonapartes.", "warandpeace", false),
        QuizElement("“The Signora had no business to do it,” said Miss Bartlett, “no business at all.", "room", false),
        QuizElement("\"Christmas won't be Christmas without any presents,\" grumbled Jo, lying on the rug", "littlewoman", false),
        QuizElement("Cardinal de Retz very judiciously observes, that all historians must of necessity be subject to mistakes, in explaining the motives of those actions they record", "ferdinand", false)
    )

    val bookList: List<String> = listOf(
        "harrypotter",
        "winniepooh",
        "timemachine",
        "tomsawyer",
        "alice",
        "gatsby",
        "warandpeace",
        "room",
        "littlewoman",
        "ferdinand"
    )
}