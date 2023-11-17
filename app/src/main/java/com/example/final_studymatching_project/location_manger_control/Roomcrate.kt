import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.final_studymatching_project.R
import com.example.final_studymatching_project.location_manger_control.MatchingMain
import java.util.*

class Roomcrate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roomcrate)

        findViewById<Button>(R.id.cancelbtn).setOnClickListener {
            val intent = Intent(this@Roomcrate, MatchingMain::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.choose_numberbtn).setOnClickListener {
            showNumberSelectionDialog()
        }

        findViewById<Button>(R.id.choose_datebtn).setOnClickListener {
            showDatePicker()
        }
    }

    //인원수 선택 다이얼로그
    private fun showNumberSelectionDialog() {
        val numbers = arrayOf("1~2명", "2~4명", "2~6명", "4~8명")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("인원수 선택")
        builder.setItems(numbers) { dialog, which ->
            val selectedNumber = numbers[which]
            Toast.makeText(this, "선택된 인원수: $selectedNumber", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("취소") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
 // 캘린더 api
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDayOfMonth"
            Toast.makeText(this, "선택된 날짜: $selectedDate", Toast.LENGTH_SHORT).show()
        }, year, month, dayOfMonth)

        datePickerDialog.show()
    }
}
