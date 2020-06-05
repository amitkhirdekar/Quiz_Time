package com.example.trivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.trivia.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    data class Question(
            val text: String,
            val answers: List<String>)

    // The first answer is the correct one.  We randomize the answers before showing the text.
    // All questions must have four answers.  We'd want these to contain references to string
    // resources so we could internationalize. (Or better yet, don't define the questions in code...)
    private val questions: MutableList<Question> = mutableListOf(

        //General Questions
            Question(text = "How many times a day do a clock’s hands overlap ?",
                    answers = listOf("22 times", "12 times", "24 times", "0 times")),
            Question(text = "Suppose Tom is 16-year-old, and he is four times older than Jerry. How old Tom would be when he is twice as old as Jerry ?",
                    answers = listOf("24 years", "12 years", "32 years", "64 years")),
            Question(text = " Brother and sisters I have none but this man’s father is my father’s son? Who is the Man?",
                    answers = listOf("His Son", "His Daughter ", "He himself ", "His Father ")),
            Question(text = "The day before the day before yesterday is three days after Saturday. What day is today?",
                    answers = listOf("Friday", "Thursday", "Saturday ", "Monday")),
            Question(text = "Divide 30 by ½ and then add ten to it, the answer is: ",
                    answers = listOf("70", "5", "60", "25")),
            Question(text = "If South-East becomes North, North-East becomes West and so on. What will West become? ",
                    answers = listOf("South-East ", "North-East ", "North-West ", "South-West ")),
            Question(text = "If the price of a book is first decreased by 25% and then increased by 20%, then the net change in the price will be:",
                    answers = listOf("10 %", "5 %", "20 %", "30 %")),
            Question(text = "The product of two numbers is 2028 and their H.C.F. is 13. The number of such pairs is:",
                    answers = listOf("2", "1", "3", "4")),
            Question(text = "The average of 20 numbers is zero. Of them, at the most, how many may be greater than zero?",
                    answers = listOf("19", "10", "0", "18")),
            Question(text = "Which of the following is used in pencils?",
                    answers = listOf("Graphite", "Silicon", "Charcoal", "Phosphorous")),

        //Chemistry and Physics Questions
            Question(text = "The gas usually filled in the electric bulb is ",
                    answers = listOf("nitrogen", "hydrogen", "carbon dioxide", "oxygen")),
            Question(text = "Galvanised iron sheets have a coating of ",
                    answers = listOf("zinc", "lead", "chromium", "tin")),
            Question(text = "Heavy water is ",
                    answers = listOf("deuterium oxide", "PH7", "rain water", "tritium oxide")),
            Question(text = "The most important ore of aluminium is",
                    answers = listOf("bauxite", "galena", "calamine", "calcite")),
            Question(text = "Which of the following is in liquid form at room temperature?",
                    answers = listOf("Francium", "Lithium", "Sodium", "Cerium")),
            Question(text = "What is laughing gas?",
                    answers = listOf("Nitrous Oxide", "Carbon monoxide", "Sulphur dioxide", "Hydrogen peroxide")),
            Question(text = "In fireworks, the green flame is produced because of",
                    answers = listOf("barium", "sodium", "mercury", "potassium")),
            Question(text = "Fathom is the unit of ",
                    answers = listOf("depth", "sound", "frequency", "distance")),
            Question(text = "Electric current is measure by ",
                    answers = listOf("ammeter", "commutator", "anemometer", "voltmeter")),
            Question(text = "In an atomic nucleus, neutrons and protons are held together by ",
                    answers = listOf("exchange forces", "coulombic forces", "gravitational forces", "magnetic forces")),

        //Animals and Birds Questions
            Question(text = "Which is the largest living bird on Earth?",
                    answers = listOf("Ostrich", "Emu", "Albatross", "Siberian Crane")),
            Question(text = "Which animal fasts for about 8 months in a year and yet is active, gives birth, and nurses its young while fasting?",
                    answers = listOf("Polar Bear", "Frog", "Reindeer", "Lion")),
            Question(text = "In which of the following species does the male have mammary glands to feed milk to its young?",
                    answers = listOf("Dayak fruit bat", "Sunda flying lemur", "Blue Whale", "Polar bear")),
            Question(text = "For which one of the following snakes is the diet mainly composed of other snakes?",
                    answers = listOf("King Cobra", "Krait", "Russel's viper", "Rattlesnake")),
            Question(text = "Which phenomenon do bats or dolphins use to find prey, predators or obstacles?",
                    answers = listOf("Echo location", "Refraction of sound", "Formation of beats", "Scattering")),
            Question(text = "Which of the following is the National Aquatic Animal of India?",
                    answers = listOf("Dolphin", "Salt Water Crocodile", "Sea Turtle", "Dugong")),
            Question(text = "The release of which one of the following into ponds and wells helps in controlling the mosquitoes?",
                    answers = listOf("Gambusia fish", "Crab", "Dogfish", "Snail")),
            Question(text = "Darwin finches refers to a group of",
                    answers = listOf("Birds", "Fishes", "Lizards", "Amphibians")),
            Question(text = "Which amongst the following is the largest mammal?",
                    answers = listOf("Whale", "Elephant", "Dinosaur", "Rhinoceros")),
            Question(text = "Which part becomes modified as the tusk of elephant?",
                    answers = listOf("Second Incisor", "Canine", "Premolar", "Molar")),

        //World and Earth Questions
            Question(text = "What is the largest desert in the world?",
                    answers = listOf("Sahara Desert", "Arabian Desert", "Gobi Desert", "Great Victoria")),
            Question(text = "Which is the biggest underground railway network?",
                    answers = listOf("London", "New York", "Moscow", "Paris")),
            Question(text = "The Council of States in India is generally known as:",
                    answers = listOf("Rajya Sabha", "Lok Sabha", "Parliament", "AD hoc Committee")),
            Question(text = "In which direction earth spins on its axis?",
                    answers = listOf("West to East", "East to West", "South to North", "North to South")),
            Question(text = "Which is the largest freshwater lake in the world?",
                    answers = listOf("Lake Superior", "Caspian Sea", "Lake Baikal", "Lake Titicaca")),
            Question(text = "Which is the third largest ocean in the world?",
                    answers = listOf("Indian Ocean", "Pacific Ocean", "Atlantic Ocean", "Arctic Ocean")),
            Question(text = "Which is the oldest mountain?",
                    answers = listOf("Aravallis", "Himalayas", "Andes", "Alps")),
            Question(text = "How long does Earth takes to complete one rotation?",
                    answers = listOf("23 Hours, 56 minutes", "23 Hours, 55 minutes", "23 Hours, 54 minutes", "22 Hours, 60 minutes")),
            Question(text = "Which one of the following is the cause for a change in the season?",
                    answers = listOf("Earth’s Revolution", "Earth’s Rotation", "Earth’s Rotation and Revolution", "None of the these")),
            Question(text = "Which is the oldest religion in the world?",
                    answers = listOf("Hinduism", "Christian", "Islam", "Buddhism")),

        //Current Affairs Questions
            Question(text = "UNSC has how many non-permanent members?",
                    answers = listOf("10", "5", "17", "20")),
            Question(text = "In which Chinese city did the coronavirus start?",
                    answers = listOf("Wuhan", "Bejing", "Shanghai", "None of these")),
            Question(text = "Which private company became the first to launch a rocket into space?",
                    answers = listOf("SpaceX", "NASA", "ISRO", "ROSCOSMOS")),
            Question(text = "Which film won Best Picture at the Oscars?",
                    answers = listOf("Parasite", "Joker", "The Irishman", "Little Women")),
            Question(text = "On what date the World Health Organisation declare the coronavirus a pandemic?",
                    answers = listOf("March 11", "January 11", "February 11", "April 11")),
            Question(text = "Which country has joined an international panel of G7 for setting up ethical guidelines for the use of artificial intelligence?",
                    answers = listOf("USA", "France", "UK", "Canada")),
            Question(text = "UNICEF partnered with which company to support the school children in the coronavirus affected areas and provide them access to remote learning in africa?",
                    answers = listOf("Bharti Airtel", "Reliance", "Idea Cellular", "Tata Indicom")),
            Question(text = "Which country has become the first country in the whole of Europe to declare an end to its COVID-19 outbreak?",
                    answers = listOf("Slovenia", "Italy", "Germany", "Spain")),
            Question(text = "Which company has announced a new policy that will allow all of its employees to work from home, not only currently in the wake of the coronavirus pandemic but also indefinitely afterward?",
                    answers = listOf("Twitter", "Instagram", "Snapchat", "Facebook")),

        //Last Question ;)
            Question(text = " A girl introduced a boy as the son of the daughter of the father of her uncle. The boy is girl's ____. ",
                    answers = listOf("Brother", "Son", "Uncle", "Son-in-law"))
    )



    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = Math.min((questions.size + 1) / 2, 10)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
                inflater, R.layout.fragment_game, container, false)

        // Shuffles the questions and sets the question index to the first question.
        randomizeQuestions()

        // Bind this fragment class to the layout
        binding.game = this

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }
                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    Toast.makeText(context,"Correct Answer !",Toast.LENGTH_SHORT).show()
                    questionIndex++
                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        view.findNavController()
                            .navigate(GameFragmentDirections
                                .actionGameFragmentToGameWonFragment(numQuestions, questionIndex))
                        // We've won!  Navigate to the gameWonFragment.
                    }
                } else {
                    // Game over! A wrong answer sends us to the gameOverFragment.
                    Toast.makeText(context,"Opps! Wrong Answer!", Toast.LENGTH_SHORT).show()
                    view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment())
                }
            }
        }
        return binding.root
    }

    // randomize the questions and set the first question
    private fun randomizeQuestions() {
        questions.shuffle()
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }


}
