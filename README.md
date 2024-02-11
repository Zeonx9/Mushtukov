
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84.svg?style=for-the-badge&logo=android-studio&logoColor=white)


Тестовое задание для отбора на Финтех Android зима 2024.
Цель задания - написать приложение для просмотра списка фильмов с кинопоиска.

## Использованные библиотеки

Проект написан на Kotlin. UI написан с помощью Jetpack Compose.
Также испоьзованы:

* Dagger-Hilt - для внедрения зависимостей
* Retrofit - для работы с  API Кинопоиска
* Room - для локальной базой данных
* Compose Navigation - для навигации между экранами
* Сoil - для асинхронной подгрузки изображений

## Функционал приложения

#### Отображение списка популярных фильмов.
<img alt="Скриншот - популярные фильмы" src="screenshots/photo_5_2024-02-11_23-05-08.jpg" width="200"/>

#### Отображение детальной информации о выбранном фильме.
<img alt="Скриншот - детали фильма" src="screenshots/photo_2024-02-11_23-10-08.jpg" width="200"/>

#### Сохранение и удаление фильмов в избранное и из него.
<img alt="Скриншот - избранное" src="screenshots/photo_3_2024-02-11_23-05-08.jpg" width="200"/>

#### Поиск по названию фильма в списке популярных фильмов и в избранном.
<img alt="Скриншот - посик по названию" src="screenshots/photo_4_2024-02-11_23-05-08.jpg" width="200"/>

#### Aдаптивное отображение списка фильмов - при повороте экрана также отображается информация о выбранном фильме.
<img alt="Скриншот - адаптивное отображение" src="screenshots/photo_2_2024-02-11_23-05-08.jpg" width="500" />

#### При отстувии сети, пользователь уведомляется об ошибках
<img alt="Скриншот - отсутвие сети" src="screenshots/photo_1_2024-02-11_23-05-08.jpg" width="200"/>



