package com.tensesapp.data.model

import com.tensesapp.R

object EnglishConstants {
    fun getSimplePresentQuestions(): ArrayList<EnglishQuestion> {
        val questionList = ArrayList<EnglishQuestion>()

        val queOne = EnglishQuestion(
            1,
            "The sun _____ in the east every morning.",
            R.drawable.sp1,
            Pair("rise", 1),
            Pair("rose", 2),
            Pair("rises", 3),
            3
        )

        val queTwo = EnglishQuestion(
            2,
            "I usually _____ coffee for breakfast.",
            R.drawable.sp2,
            Pair("drink", 1),
            Pair("drank", 2),
            Pair("drinks", 3),
            1
        )

        val queThree = EnglishQuestion(
            3,
            "Simpanse ____ a smart animal.",
            R.drawable.sp3,
            Pair("is", 1),
            Pair("are", 2),
            Pair("does", 3),
            1
        )

        val queFour = EnglishQuestion(
            4,
            "Jakarta _____ never truly sleep.",
            R.drawable.sp4,
            Pair("are", 1),
            Pair("were", 2),
            Pair("is", 3),
            3
        )

        val queFive = EnglishQuestion(
            5,
            "Does your brother _____ breakfast?",
            R.drawable.sp5,
            Pair("eat", 1),
            Pair("ate", 2),
            Pair("eats", 3),
            1
        )

        val queSix = EnglishQuestion(
            6,
            "The library _____ on Sundays.",
            R.drawable.sp6,
            Pair("closes", 1),
            Pair("closed", 2),
            Pair("close", 3),
            1
        )

        val queSeven = EnglishQuestion(
            7,
            "My cat always _____ to play ball.",
            R.drawable.sp7,
            Pair("love", 1),
            Pair("loves", 2),
            Pair("loved", 3),
            2
        )

        val queEight = EnglishQuestion(
            8,
            "In the evenings, Egy _____ to watch TV.",
            R.drawable.sp8,
            Pair("like", 1),
            Pair("likes", 2),
            Pair("liked", 3),
            2
        )

        val queNine = EnglishQuestion(
            9,
            "____ trees lose their leaves in the fall?",
            R.drawable.sp9,
            Pair("do", 1),
            Pair("did", 2),
            Pair("does", 3),
            1
        )

        val queTen = EnglishQuestion(
            10,
            "Rini usually _____ the bus to school every day.",
            R.drawable.sp10,
            Pair("took", 1),
            Pair("takes", 2),
            Pair("take", 3),
            2
        )

        questionList.addAll(listOf(queOne, queTwo, queThree, queFour, queFive, queSix, queSeven, queEight, queNine, queTen))

        return questionList
    }

    fun getSimplePastQuestions(): ArrayList<EnglishQuestion> {
        val questionList = ArrayList<EnglishQuestion>()

        val queOne = EnglishQuestion(
            1,
            "Jack ________ the giant and stole the golden hen last year.",
            R.drawable.ss1,
            Pair("kills", 1),
            Pair("killing", 2),
            Pair("killed", 3),
            3
        )

        val queTwo = EnglishQuestion(
            2,
            "The little mermaid ________ her voice to the sea witch in exchange for legs a long time ago.",
            R.drawable.ss2,
            Pair("sells", 1),
            Pair("sell", 2),
            Pair("sold", 3),
            3
        )

        val queThree = EnglishQuestion(
            3,
            "The wolf ________ the three little pigs' houses but failed to blow down the brick one yesterday.",
            R.drawable.ss3,
            Pair("blow", 1),
            Pair("blowing", 2),
            Pair("blew", 3),
            3
        )

        val queFour = EnglishQuestion(
            4,
            "Snow White ________ by biting into a poisoned apple a while ago.",
            R.drawable.ss4,
            Pair("dies", 1),
            Pair("dying", 2),
            Pair("died", 3),
            3
        )

        val queFive = EnglishQuestion(
            5,
            "King Arthur ________ the sword Excalibur from the stone, proving his rightful claim to the throne many years ago.",
            R.drawable.ss5,
            Pair("pulls", 1),
            Pair("pulled", 2),
            Pair("pull", 3),
            2
        )

        val queSix = EnglishQuestion(
            6,
            "Hansel and Gretel ________ a trail of breadcrumbs to find their way home a few days ago.",
            R.drawable.ss6,
            Pair("leaves", 1),
            Pair("leave", 2),
            Pair("left", 3),
            3
        )

        val queSeven = EnglishQuestion(
            7,
            "Did Robin Hood ________ from the rich and gives to the poor last month?",
            R.drawable.ss7,
            Pair("steals", 1),
            Pair("steal", 2),
            Pair("stole", 3),
            2
        )

        val queEight = EnglishQuestion(
            8,
            "Our English exam ____ yesterday.",
            R.drawable.ss8,
            Pair("was", 1),
            Pair("were", 2),
            Pair("is", 3),
            1
        )

        val queNine = EnglishQuestion(
            9,
            "The genie ________ back into the lamp after granting the wishes last night.",
            R.drawable.ss9,
            Pair("is going", 1),
            Pair("went", 2),
            Pair("go", 3),
            2
        )

        val queTen = EnglishQuestion(
            10,
            "Little Red Riding Hood ________ to her grandmother's house through the forest last week.",
            R.drawable.ss10,
            Pair("walked", 1),
            Pair("walking", 2),
            Pair("walks", 3),
            1
        )

        questionList.addAll(listOf(queOne, queTwo, queThree, queFour, queFive, queSix, queSeven, queEight, queNine, queTen))

        return questionList
    }

    fun getPresentContinuousQuestions(): ArrayList<EnglishQuestion> {
        val questionList = ArrayList<EnglishQuestion>()

        val queOne = EnglishQuestion(
            1,
            "Nabilla ___________ a movie right now.",
            R.drawable.pc1,
            Pair("is watching", 1),
            Pair("watches", 2),
            Pair("watched", 3),
            1
        )

        val queTwo = EnglishQuestion(
            2,
            "Kevin ___________ his homework right now.",
            R.drawable.pc2,
            Pair("is doing", 1),
            Pair("does", 2),
            Pair("did", 3),
            1
        )

        val queThree = EnglishQuestion(
            3,

            "___________ you listening to music at the moment?",
            R.drawable.pc3,
            Pair("Do", 1),
            Pair("Are", 2),
            Pair("Is", 3),
            2
        )

        val queFour = EnglishQuestion(
            4,

            "We ___________ basketball right now.",
            R.drawable.pc4,
            Pair("is playing", 1),
            Pair("play", 2),
            Pair("are playing", 3),
            3
        )

        val queFive = EnglishQuestion(
            5,
            "Chef ___________ a delicious meal now.",
            R.drawable.pc5,
            Pair("are cooking", 1),
            Pair("cook", 2),
            Pair("is cooking", 3),
            3
        )

        val queSix = EnglishQuestion(
            6,
            "____________ she reading a book at the moment?",
            R.drawable.pc6,
            Pair("Is", 1),
            Pair("Does", 2),
            Pair("Are", 3),
            1
        )

        val queSeven = EnglishQuestion(
            7,
            "We ___________ a new recipe right now.",
            R.drawable.pc7,
            Pair("are trying", 1),
            Pair("trying", 2),
            Pair("tried", 3),
            1
        )

        val queEight = EnglishQuestion(
            8,
            "____________ you waiting for someone right now?",
            R.drawable.pc8,
            Pair("Is", 1),
            Pair("Does", 2),
            Pair("Are", 3),
            3
        )

        val queNine = EnglishQuestion(
            9,
            "____________ Raffi running a marathon now?",
            R.drawable.pc9,
            Pair("Do", 1),
            Pair("Does", 2),
            Pair("Is", 3),
            3
        )

        val queTen = EnglishQuestion(
            10,
            "The students ___________ a book at the moment.",
            R.drawable.pc10,
            Pair("is reading", 1),
            Pair("read", 2),
            Pair("are reading", 3),
            3
        )

        questionList.addAll(listOf(queOne, queTwo, queThree, queFour, queFive, queSix, queSeven, queEight, queNine, queTen))

        return questionList
    }
    fun getPresentPerfectQuestions(): ArrayList<EnglishQuestion> {
        val questionList = ArrayList<EnglishQuestion>()

        val queOne = EnglishQuestion(
            1,
            "I've been waiting for the bus for over an hour.  Have you _____ for a long time too?",
            R.drawable.pp1,
            Pair("wait", 1),
            Pair("waited", 2),
            Pair("been waiting", 3),
            2
        )

        val queTwo = EnglishQuestion(
            2,
            "We _____ to the beach yet this summer.",
            R.drawable.pp2,
            Pair("haven't gone", 1),
            Pair("didn't go", 2),
            Pair("not going", 3),
            1
        )

        val queThree = EnglishQuestion(
            3,
            "She _____ three novels this month!  She's such a fast reader.",
            R.drawable.pp3,
            Pair("has read", 1),
            Pair("read", 2),
            Pair("is reading", 3),
            1
        )

        val queFour = EnglishQuestion(
            4,
            "Their rooms  _____ clean yet.  It looks a bit messy.",
            R.drawable.pp4,
            Pair("haven't been", 1),
            Pair("were", 2),
            Pair("hasn't been", 3),
            1
        )

        val queFive = EnglishQuestion(
            5,
            "_____ you ever been to Paris?  It's a beautiful city.",
            R.drawable.pp5,
            Pair("Did", 1),
            Pair("Do", 2),
            Pair("Have", 3),
            3
        )

        val queSix = EnglishQuestion(
            6,
            "I _____ finished my homework yet.  Would you like to help me?",
            R.drawable.pp6,
            Pair("have", 1),
            Pair("haven't", 2),
            Pair("am not", 3),
            2
        )

        val queSeven = EnglishQuestion(
            7,
            "My friends _____ the movie yet, so we can still go see it if you want.",
            R.drawable.pp7,
            Pair("have not watched", 1),
            Pair("has not watched", 2),
            Pair("are not watching", 3),
            1
        )

        val queEight = EnglishQuestion(
            8,
            "My brother _____ his hair yet this morning.  He looks a bit messy!",
            R.drawable.pp8,
            Pair("hasn't combed", 1),
            Pair("didn't comb", 2),
            Pair("isn't combing", 3),
            1
        )

        val queNine = EnglishQuestion(
            9,
            "We _____ this recipe before, but it's always delicious.",
            R.drawable.pp9,
            Pair("have made", 1),
            Pair("made", 2),
            Pair("are making", 3),
            1
        )

        val queTen = EnglishQuestion(
            10,
            "She has lost her phone!  Have you _____ it anywhere?",
            R.drawable.pp10,
            Pair("seen", 1),
            Pair("see", 2),
            Pair("saw", 3),
            1
        )

        questionList.addAll(listOf(queOne, queTwo, queThree, queFour, queFive, queSix, queSeven, queEight, queNine, queTen))

        return questionList
    }
    fun getSimpleFutureQuestions(): ArrayList<EnglishQuestion> {
        val questionList = ArrayList<EnglishQuestion>()

        val queOne = EnglishQuestion(
            1,
            "Firza _____ her homework after dinner.",
            R.drawable.sf1,
            Pair("will do", 1),
            Pair("does", 2),
            Pair("did", 3),
            1
        )

        val queTwo = EnglishQuestion(
            2,
            "Dono and Tian _____ to the party if it rains.",
            R.drawable.sf2,
            Pair("won't come", 1),
            Pair("aren't coming", 2),
            Pair("don't come", 3),
            1
        )

        val queThree = EnglishQuestion(
            3,
            "_____ you be able to help me with this project tomorrow?",
            R.drawable.sf3,
            Pair("Will", 1),
            Pair("Are", 2),
            Pair("Were", 3),
            1
        )

        val queFour = EnglishQuestion(
            4,
            "Lexy is baking a cake. It _____ delicious!",
            R.drawable.sf4,
            Pair("will be", 1),
            Pair("is", 2),
            Pair("was", 3),
            1
        )

        val queFive = EnglishQuestion(
            5,
            "I'm not sure what to wear to the wedding. It _____ formal, I think.",
            R.drawable.sf5,
            Pair("will be", 1),
            Pair("needs to", 2),
            Pair("needed to", 3),
            1
        )

        val queSix = EnglishQuestion(
            6,
            "The train _____ there in ten minutes. Be ready!",
            R.drawable.sf6,
            Pair("will be", 1),
            Pair("is", 2),
            Pair("has been", 3),
            1
        )

        val queSeven = EnglishQuestion(
            7,
            "After she graduates, Sofia _____ her study in University.",
            R.drawable.sf7,
            Pair("will continue", 1),
            Pair("will be continuing", 2),
            Pair("continued", 3),
            1
        )

        val queEight = EnglishQuestion(
            8,
            "We _____ to the beach if the weather is nice.",
            R.drawable.sf8,
            Pair("go", 1),
            Pair("will go", 2),
            Pair("went", 3),
            2
        )

        val queNine = EnglishQuestion(
            9,
            "Charlotte _____ a good magician someday.",
            R.drawable.sf9,
            Pair("is", 1),
            Pair("will be", 2),
            Pair("was", 3),
            2
        )

        val queTen = EnglishQuestion(
            10,
            "They _____ the test tomorrow, so they're studying hard today.",
            R.drawable.sf10,
            Pair("are taking", 1),
            Pair("will take", 2),
            Pair("took", 3),
            2
        )

        questionList.addAll(listOf(queOne, queTwo, queThree, queFour, queFive, queSix, queSeven, queEight, queNine, queTen))

        return questionList
    }
}