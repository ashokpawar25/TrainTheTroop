# TrainTheTroop

##Problem Statement

As a gaming programmer you have to create a simulation software using which gamers can train army troops.
Definition and details of the simulation is as follows

Army Troops:
There are two kinds of troops Archers and Barbarians. Each trooper(archer/barbarian)
Trooper Training Time (seconds) Training Cost (magic potions)

Barbarian(weapon: sword) 3 10

Archer(weapon: Bow and Arrow) 6 20

Barracks: where each trooper gets trained.
Only one trooper can be trained at a given point of time.
The maximum seat capacity of a barracks is 10.
Others have to wait to outside the barracks
Barracks can have mix of troopers: e.g.: 5 Archers and 5 Barbarians or 4 Archers and 6 Barbarians or 10 Archers or 10 Barbarians
Army Camp: where troops assemble after training.
Scenario 1: Simulate the training and train barbarians and/or archers.
As a gamer
1. i should be able to input no.of barbarians and/or archers i would like to train.
2. i should be able to see how many troops are trained and available in troop camp after training completes.
Example input/output:

troop-training > start-training
1. train troops.
2. view troop camp
What do you want to do ? 1

1. Archer
2. Barbarian
which troop u want to train ? 1
how many archers you want to train ? 10
training...
training complete. troops are available in the troop camp.

1. train troops.
2. view troop camp
What do you want to do ? 2
Archers: 10
Barbarian: 0

1. train troops.
2. view troop camp
What do you want to do ? 1

Expectations:
1. You are required to help create the software that works according to the sample inputs applied and generates output as shown in
the Input-Output section shown for the problem.
2. You should demonstrate the working software by building a console application or writing test program that exercises the sample
inputs, for this purpose you may use jUnit or any other testing framework.

# domain Package - It consist domain models and domain services

## Trooper Class

### States:
- `private final int` **trainingTime**
- `private final int` **trainingCost**
- `private final Weapon` **weapon**

### Constructor:
- `public Trooper(int trainingTime, int trainingCost, Weapon weapon)`

### Behaviours:
- `public int` **getTrainingTime()**
- `@Override public boolean` **equals(Object o)**

## Archer Class

### Constructor:
- `public Archer(int trainingTime, int trainingCost, Weapon weapon)`

### Description:
- Inherits from `Trooper` class.

## Barbarian Class

### Constructor:
- `public Barbarian(int trainingTime, int trainingCost, Weapon weapon)`

### Description:
- Inherits from `Trooper` class.

## Weapon Enum

### Values:
- `BOW_AND_ARROW`
- `SWORD`

### Description:
- Represents the available weapons for troops.

# controller Package - This package having controller classes

## ArmyCampController Class

### States:
- `ArmyCampService` **armyCampService**

### Constructor:
- `public ArmyCampController(ArmyCampService armyCampService)`

### Behaviours:
- `public List<Trooper>` **getTrainedTroopers()**
- `public Response` **addTrooperToCamp(Trooper trooper)**
- `public long` **getTrooperCount(TroopType troopType)**

## BarrackController Class

### States:
- `private final BarrackService` **barrackService**

### Constructor:
- `public BarrackController(BarrackService barrackService)`

### Behaviours:
- `public Response` **addTrooperToBarrack(int archerCount, int barbarianCount)**
- `public Queue<Trooper>` **getTroopers()**
- `public Response` **trainTheTrooper()**

## TrooperController Class

### States:
- `private final TrooperService` **trooperService**

### Constructor:
- `public TrooperController(TrooperService trooperService)`

### Behaviours:
- `public Response` **create(TroopType type, int trainingTime, int trainingCost, Weapon weapon)**
- `public List<Trooper>` **getTroopers()**
- `public List<Trooper>` **getTroopersWithCount(int archerCount, int barbarianCount)**

## controller.dto Package - This package consist class to return http status from the controller methods

## HttpStatus Enum
### Values:
- `BADREQUEST`
- `OK`

## Response Class
### States:
- `public HttpStatus` **status**
- `public String` **message**

### Constructor:
- `public Response(HttpStatus status, String message)`

# service Package - Classes from this package are the middalware between controller layer and repository layer

## ArmyCampService Class

### States:
- `ArmyCampRepository` **armyCampRepository**

### Constructor:
- `public ArmyCampService(ArmyCampRepository armyCampRepository)`

### Behaviours:
- `public void` **addTrooperToCamp(Trooper trooper)**
- `public List<Trooper>` **getTrainedTroopers()**
- `public long` **getTrooperCountFor(TroopType troopType)**

## BarrackService Class

### States:
- `BarrackRepository` **barrackRepository**
- `ArmyCampService` **armyCampService**
- `TrooperService` **trooperService**

### Constructor:
- `public BarrackService(BarrackRepository barrackRepository, ArmyCampService armyCampService, TrooperService trooperService)`

### Behaviours:
- `public void` **addTroopers(int archerCount, int barbarianCount)**
- `public Queue<Trooper>` **getTroopersFromBarrack()**
- `public synchronized void` **trainTheTrooper()**

## TrooperService Class

### States:
- `private final TrooperRepository` **trooperRepository**

### Constructor:
- `public TrooperService(TrooperRepository trooperInMemoryRepository)`

### Behaviours:
- `public Trooper` **create(TroopType type, int trainingTime, int trainingCost, Weapon weapon)**
- `public List<Trooper>` **getTroopers()**
- `public List<Trooper>` **getTroopersWithCount(int archerCount, int barbarianCount)**

# Repository Package - Classes from this package interact with the database, performing operations requested by the services.

## InMemoryArmyCampRepository Class

### States:
- `InMemoryDatabase` **inMemoryDatabase**

### Constructor:
- `public InMemoryArmyCampRepository(InMemoryDatabase inMemoryDatabase)`

### Behaviours:
- `@Override public void` **add(Trooper trooper)**
- `@Override public List<Trooper>` **getTrainedTroopers()**

## InMemoryBarrackRepository Class

### States:
- `InMemoryDatabase` **inMemoryDatabase**

### Constructor:
- `public InMemoryBarrackRepository(InMemoryDatabase inMemoryDatabase)`

### Behaviours:
- `@Override public void` **addTroopers(Queue<Trooper> trooperQueue)**
- `@Override public Queue<Trooper>` **getTroopersFromBarrack()**

## InMemoryTrooperRepository Class

### States:
- `private final InMemoryDatabase` **inMemoryDatabase**

### Constructor:
- `public InMemoryTrooperRepository(InMemoryDatabase inMemoryDatabase)`

### Behaviours:
- `@Override public Trooper` **insert(Trooper trooper)**
- `@Override public List<Trooper>` **getTroopers()**
- `@Override public List<Trooper>` **getTroopersWithCount(int archerCount, int barbarianCount)**
