# Задание лабораторной работы №4
Переработать программу, созданную в результате выполнения лабораторной работы #3, следующим образом:

- Для управления бизнес-процессом использовать BPM-движок Camunda.
- Заменить всю "статическую" бизнес-логику на "динамическую" на базе BPMS. Весь бизнес-процесс, реализованный в ходе выполнения предыдущих лабораторных работ (включая разграничение доступа по ролям, управление транзакциями, асинхронную обработку и периодические задачи), должен быть сохранён!.
- BPM-движок должен быть встроен в веб-приложение (embedded mode).
- Для описания бизнес-процесса необходимо использовать приложение Camunda Modeler.
- Пользовательский интерфейс приложения должен быть сгенерирован с помощью генератора форм Camunda.
- Итоговая сборка должно быть развернута на сервере helios под управление сервера приложений Apache Tomcat.

### Правила выполнения работы:
- Описание бизнес-процесса необходимо реализовать на языке BPMN 2.0.
- Необходимо интегрировать в состав процесса, управляемого BPMS, всё, что в принципе возможно в него интегрировать. Если какой-то из компонентов архитектуры приложения (например, асинхронный обмен сообщениями с помощью JMS) не поддерживается, необходимо использовать для интеграции с этой подсистемой соответствующие API и адаптеры.
- Распределенную обработку задач и распределенные транзакции на BPM-движок переносить не требуется.