package com.shadow.taller01_test_1.ui.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ToDoCard(title: String, description: String, start: String, end: String, onClick: ()-> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 8.dp, end = 8.dp)
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold

        )
        //Spacer(modifier = Modifier.padding(2.dp))
        Text(
            modifier = Modifier.padding(4.dp),
            text = description,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis
        )
        //Spacer(modifier = Modifier.padding(2.dp))

        Text(
            modifier = Modifier.padding(4.dp),
            text = start + " - " + end
        )
        Button(
            onClick =
                    onClick
            ) {
            Icon(Icons.Default.Edit, contentDescription = "Edit")
        }

    }
}

@Preview
@Composable
private fun ComponentPreview() {
    ToDoCard(
        title = "Example title",
        description = "description example",
        start = "25/05",
        end = "26/05",
        onClick = {}
    )
}