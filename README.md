# Rock Paper Scissors

[![GitHub Workflow Status](https://img.shields.io/github/workflow/status/eledhwen/rps/Java%20CI%20with%20Maven)](https://github.com/eledhwen/rps/actions?query=workflow%3A%22Java+CI+with+Maven%22)
![GitHub](https://img.shields.io/github/license/eledhwen/rps)

A very simple Rock Paper Scissors CLI game with a handful of simple AI strategies.

_Note: This project is considered as "in beta" and as such significant API changes may occur without prior warning._

## I. Build

This project will require you to have the following:

* Java 13+
* Git (versioning)
* Maven (dependency resolving, publishing and packaging) 

After cloning the repository, simply run:

```shell
mvn package
```

## II. How to Play

For launching commands, simply run the jar and provide a valid command name, eg. `play`:

```shell
java -jar target/rock-paper-scissors.jar play
```

### A. The `play` command

The `play` command currently accepts 3 optional parameters:

* `--player` allows you to change your nickname (defaults to `anon`)
* `--rounds` allows you to change the number of rounds (defaults to `3`)
* `--strategy` allows you to select an AI strategy (defaults to `markov_chain`, possible values are `random`, `mirror` and `markov_chain`) 

Running `play` without arguments should display the following:

```
[Info] Running "play" : Starts a game of Rock Paper Scissors.

~> Starting a game with 3 rounds

#1   anon's play:  
```

Whenever the game prompts you, you can type one of the following actions:
* `ROCK`, `PAPER`, `SCISSORS` should be obvious
* `CONCEDE` will abort the game, which will result in an automatic defeat

If you need to exit the game prematurely without conceding, a simple <kbd>ctrl</kbd> + <kbd>c</kbd> should do the trick.

### B. Other commands

You can list available commands running the jar without arguments, or with the `list` command.

```shell
java -jar target/rock-paper-scissors.jar
```

This should give you the following output:

```
[Info] Running "list" : Lists all defined tasks.

help          Shows details about a task.
list          Lists all defined tasks.
play          Starts a game of Rock Paper Scissors.

Exiting.
```

The `help` command can give you additional informations regarding a task, such as available parameters, default values, etc.

For instance at the time of this writing `help play` will give the following output:

```
[Info] Running "help" : Shows details about a task.

~> Name: play
~> Description: Starts a game of Rock Paper Scissors.
~> Usage:

	play --player {player?[anon]} --rounds {rounds?[3]} --strategy {strategy?[MARKOV_CHAIN]}

Exiting.
```