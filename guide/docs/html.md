# HTML

SimpleKotlinMail provides a convenient way for setting the HTML content of a message. Instead of just passing in the
HTML text, you can use the HTML DSL of [kotlinx.html](https://github.com/Kotlin/kotlinx.html) to create the HTML
content.

## Set HTML content

Inside your email builder, use the `withHTML` function.

```kotlin
emailBuilder {
    withHTML {
        body {
            h1 { +"Really important question:" }
            p { +"Hey, how are you today?" }
        }
    }
}
```

#### What are the advantages of using kotlinx.html?

In the above example, there isn't any real advantage apart from the better readability. But as soon as you want to
customize the messages, for example for a specific user, kotlinx.html shows its full power.

Let's say that we want to greet the user with his name. Additionally, we want to list the items he bought:

```kotlin
val username = "foo"
val itemsBought = listOf("banana", "apple")

emailBuilder {
    withHTML {
        body {
            h1 { +"We have received your order." }
            p { +"Thanks for shopping with us $username!" }

            p { +"You have bought the following items:" }
            ul {
                for (item in itemsBought)
                    li { +item }
            }
        }
    }
}
```

Here kotlinx.html allows us to use the existing data we have (`username` and `itemsBought`) without the need for an
additional templating language.
