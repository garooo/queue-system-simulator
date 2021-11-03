# queue-simulator

## Setup

### .vscode content

Contenuto da inserire all'interno della dir .vscode in caso si scelga visual studio code come editor

#### launch.json

```json
{
    // Usare IntelliSense per informazioni sui possibili attributi.
    // Al passaggio del mouse vengono visualizzate le descrizioni degli attributi esistenti.
    // Per altre informazioni, visitare: https://go.microsoft.com/fwlink/?linkid=830387
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Launch Current File",
            "request": "launch",
            "mainClass": "${file}"
        },
        {
            "type": "java",
            "name": "Launch App",
            "request": "launch",
            "mainClass": "App",
        }
    ]
}
```

#### settings.json

```json
{
    "java.project.sourcePaths": ["src"],
    "java.project.outputPath": "bin"
}
```

## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).


## Librerie Richieste

La libreria jfreecharts è richiesta per la creazione dei plots. Scaricare la versione 1.0.0 al link https://www.jfree.org/jfreechart/download/ ed estrarre l'archivio .tar.gz all'interno della directory lib presente sulla root del progetto.