import android.widget.ToggleButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.autocheck.R
import com.autocheck.ui.AutoCheckApp
import com.autocheck.ui.theme.AutoCheckTheme

@Composable
fun HomeScreen (modifier: Modifier){
    var checked by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

    ) {

        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "Hallo Martin, \n" +
                    "was würdest du gerne \n" +
                    "als nächstes tun?",
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold

        )
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        )
        {
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "News",
                fontSize = 23.sp,
                fontWeight = FontWeight.ExtraBold

            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                }


            )

        }



        Spacer(modifier = Modifier.height(25.dp))
        if (!checked) {

            Image(
                painter = painterResource(id = R.drawable.homescreen_bild),
                contentDescription = "Homescreen Car",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )
        }
        else{

            Image(
                painter = painterResource(id = R.drawable.news),
                contentDescription = "News Platzhalter",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    AutoCheckTheme {
        HomeScreen(modifier = Modifier)
    }
}