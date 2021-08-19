# Recipe

* The project is a RESTful API and is an implementation of the [Recipes](https://hyperskill.org/projects/180) project from Hyperskill

## About
* Four types of requests are supported: GET, POST, PUT, and DELETE
* HTTP Basic has been used for authentication purposes
* H2 has been used as the database
* The endpoints are the following:
  1. ```POST```: [http://localhost:8881/api/register/](http://localhost:8881/api/register/)
  ```json
  {
     "email": "heisenberg@brba.com",
     "password": "saymyname"
  }
  ```
2. ```POST```: [http://localhost:8881/api/recipe/new](http://localhost:8881/api/recipe/new) with Authentication (HTTP Basic):
    ```json
    {
       "email": "heisenberg@brba.com",
       "password": "saymyname"
    }
    ```
    and body:
    ```json
    {
       "name": "Fresh Mint Tea",
       "category": "beverage",
       "description": "Light, aromatic and refreshing beverage, ...",
       "ingredients": ["boiled water", "honey", "fresh mint leaves"],
       "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
    }
    ```
   sample response:
    ```json
    {
      "id": 1
    }
    ```
  
3. ```GET```: [http://localhost:8881/api/recipe/{recipe_id}](http://localhost:8881/api/recipe/1) with Authentication (HTTP Basic)
    ```json
    {
       "email": "heisenberg@brba.com",
       "password": "saymyname"
    }
    ```
    sample response:
    ```json
    {
      "name": "Fresh Mint Tea",
      "description": "Light, aromatic and refreshing beverage, ...",
      "category": "beverage",
      "ingredients": [
        "boiled water",
        "honey",
        "fresh mint leaves"
    ],
      "directions": [
        "Boil water",
        "Pour boiling hot water into a mug",
        "Add fresh mint leaves",
        "Mix and let the mint leaves seep for 3-5 minutes",
        "Add honey and mix again"
    ],
      "date": "2021-08-19T03:51:01.35676"
    }
    ```
4. ```PUT```: [http://localhost:8881/api/recipe/{recipe_id}](http://localhost:8881/api/recipe/1) with Authentication (HTTP Basic)
    ```json
    {
       "email": "heisenberg@brba.com",
       "password": "saymyname"
    }
    ```
    and body:
    ```json
    {
       "name": "Warming Ginger Tea",
       "category": "beverage",
       "description": "Ginger tea is a warming drink for cool weather, ...",
       "ingredients": ["1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"],
       "directions": ["Place all ingredients in a mug and fill with warm water (not too hot so you keep the beneficial honey compounds in tact)", "Steep for 5-10 minutes", "Drink and enjoy"]
    }
    ```
5. ```DELETE```[http://localhost:8881/api/recipe/{recipe_id}](http://localhost:8881/api/recipe/1) with Authentication (HTTP Basic):
    ```json
    {
       "email": "heisenberg@brba.com",
       "password": "saymyname"
    }
    ```
6. ```GET``` [http://localhost:8881/api/recipe/search/?category={category_name}](http://localhost:8881/api/recipe/search/?category=beverage)\
sample response:
```json
[
    {
        "name": "Fresh Mint Tea",
        "description": "Light, aromatic and refreshing beverage, ...",
        "category": "beverage",
        "ingredients": [
            "boiled water",
            "honey",
            "fresh mint leaves"
        ],
        "directions": [
            "Boil water",
            "Pour boiling hot water into a mug",
            "Add fresh mint leaves",
            "Mix and let the mint leaves seep for 3-5 minutes",
            "Add honey and mix again"
        ],
        "date": "2021-08-19T04:03:17.573387"
    }
]
```
