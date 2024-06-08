package com.celaenoapps.flavorquest.data.fake

import com.celaenoapps.flavorquest.data.RecipeModel
import org.intellij.lang.annotations.Language

object FakeDataSource {

    val sampleResource = RecipeModel(
        id = 1,
        title = "Spaghetti Carbonara",
        description = "A classic Italian pasta dish made with eggs, cheese, pancetta, and pepper."
    )

    @Language("json")
    val data = """
{
  "resources": [
    {
        "id": 1,
        "title": "Spaghetti Carbonara",
        "description": "A classic Italian pasta dish made with eggs, cheese, pancetta, and pepper."
    },
    {
        "id": 2,
        "title": "Chicken Tikka Masala",
        "description": "A popular Indian curry dish made with marinated chicken pieces cooked in a creamy tomato sauce."
    },
    {
        "id": 3,
        "title": "Beef Stroganoff",
        "description": "A Russian dish of sautéed beef served in a sauce with smetana (sour cream)."
    },
    {
        "id": 4,
        "title": "Vegetable Stir Fry",
        "description": "A quick and healthy dish with mixed vegetables stir-fried in a savory sauce."
    },
    {
        "id": 5,
        "title": "Chocolate Cake",
        "description": "A rich and moist chocolate cake perfect for dessert or special occasions."
    },
    {
        "id": 6,
        "title": "Caesar Salad",
        "description": "A salad made with romaine lettuce, croutons, Parmesan cheese, and Caesar dressing."
    },
    {
        "id": 7,
        "title": "Tom Yum Soup",
        "description": "A hot and sour Thai soup usually cooked with shrimp, mushrooms, tomatoes, lemongrass, galangal, and kaffir lime leaves."
    },
    {
        "id": 8,
        "title": "Tacos",
        "description": "A traditional Mexican dish consisting of small hand-sized corn or wheat tortillas topped with a filling."
    },
    {
        "id": 9,
        "title": "Sushi",
        "description": "A Japanese dish featuring vinegared rice accompanied by a variety of ingredients such as seafood, vegetables, and occasionally tropical fruits."
    },
    {
        "id": 10,
        "title": "Margherita Pizza",
        "description": "A simple pizza from Italy made with tomatoes, mozzarella cheese, fresh basil, salt, and extra-virgin olive oil."
    },
    {
        "id": 11,
        "title": "Pad Thai",
        "description": "A stir-fried rice noodle dish commonly served as street food and at casual local eateries in Thailand."
    },
    {
        "id": 12,
        "title": "Beef Bourguignon",
        "description": "A French stew made of beef braised in red wine, often red Burgundy, and beef stock, typically flavoured with carrots, onions, garlic, and a bouquet garni."
    },
    {
        "id": 13,
        "title": "Falafel",
        "description": "Deep-fried ball or patty made from ground chickpeas, fava beans, or both, originating from the Middle East."
    },
    {
        "id": 14,
        "title": "Apple Pie",
        "description": "A pie in which the principal filling ingredient is apple, often served with whipped cream, ice cream, or cheddar cheese."
    },
    {
        "id": 15,
        "title": "Paella",
        "description": "A Spanish rice dish originally from Valencia, made with saffron, a variety of seafood, meat, and vegetables."
    }
  ]
}
    """.trimIndent()
}