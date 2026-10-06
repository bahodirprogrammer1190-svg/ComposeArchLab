package uz.ttpu.composearchlab
import androidx.compose.runtime.saveable.rememberSaveable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.ttpu.composearchlab.ui.theme.ComposeArchLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeArchLabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Calling NameScreen instead of the default Greeting, passing innerPadding
                    NameScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NameScreen(modifier: Modifier = Modifier) {
    // 1. Состояние (state) хранится здесь, в "умном" компоненте
    var name by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.padding(24.dp)) {
        // 2. Передаем состояние ВНИЗ, а событие изменения ВВЕРХ
        NameField(
            name = name,
            onNameChange = { name = it }
        )
        Text("Hello, $name!")
    }
}

@Composable
fun NameField(
    name: String,
    onNameChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // 3. Этот компонент "глупый" (stateless). Он ничего сам не помнит,
    // а просто отображает то, что ему дали, и сообщает о кликах/вводе.
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Name") },
        modifier = modifier
    )
}

// 4. Превью для проверки в правой панели Android Studio без запуска эмулятора
@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun NameFieldPreview() {
    ComposeArchLabTheme {
        // Добавляем Surface, чтобы задать правильный цвет фона и текста
        androidx.compose.material3.Surface {
            NameField(
                name = "Amin",
                onNameChange = {}
            )
        }
    }
}
