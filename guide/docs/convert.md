# Convert

It is easy to convert between different email representations.

## To an email object

EML `String` to `Email`
```kotlin
string.toEmail()
```

`MimeMessage` to `Email`
```kotlin
mimeMessage.email
```

## From an email object

`Email` to `MimeMessage`
```kotlin
email.mimeMessage
```

`Email` to EML `String`
```kotlin
email.eml
```
