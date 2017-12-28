package solutions;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class CTCI_ObjectOrientedDesign {

	public static void main(String[] args) {
		
		CTCI_ObjectOrientedDesign run = new CTCI_ObjectOrientedDesign();
		
		CardDeck<BlackJackCard> cardDeck = new CardDeck<>();
		cardDeck.shuffle();
	}

}

class JigsawPiece {
	
	private Edge left, right, top, bottom;
	
	private final List<Edge> outerEdges = new ArrayList<>();
	private final List<Edge> innerEdges = new ArrayList<>();
	
	
	public JigsawPiece(Edge left, Edge right, Edge top, Edge bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
		this.populateLists();
	}
	
	private void populateLists() {
		populateInnerEdges(left);
		populateInnerEdges(right);
		populateInnerEdges(top);
		populateInnerEdges(bottom);
		
		populateOuterEdges(left);
		populateOuterEdges(right);
		populateOuterEdges(top);
		populateOuterEdges(bottom);
	}
	
	private void populateOuterEdges(Edge edge) {
		if (edge.getType().equals(EdgeType.OUTER)) {
			outerEdges.add(edge);
		}
	}
	
	private void populateInnerEdges(Edge edge) {
		if (edge.getType().equals(EdgeType.INNER)) {
			outerEdges.add(edge);
		}
	}
	
	public List<Edge> getOuterEdges() {
		return this.outerEdges;
	}
	
	public List<Edge> getInnerEdges() {
		return this.innerEdges;
	}
	
	public boolean isCornerPiece() {
		return (4 - this.innerEdges.size() - this.outerEdges.size()) >= 2; // > because in case of only 2piece puzzle, it will have 3 face edges
	}
	
	public boolean isBorderPiece() {
		return (4 - this.innerEdges.size() - this.outerEdges.size()) >= 1;
	}
}

enum EdgeType {
	INNER,
	OUTER,
	FLAT
}

class Edge {
	private boolean isMatched = false;
	private EdgeType type;
	
	public Edge(EdgeType type) {
		this.type = type;
	}
	
	public EdgeType getType() {
		return this.type;
	}
	
	public boolean isMatched() {
		return this.isMatched;
	}
	
	public void setMatched() {
		this.isMatched = true;
	}
	
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class Display {
	
	private UserReader activeUser;
	private Book activeBook; 
	private int pageNum = 0;
	
	public Display(UserReader activeUser, Book activeBook) {
		this.activeBook = activeBook;
		this.activeUser = activeUser;
		this.displayScreen();
	}
	
	public void setUser(UserReader user) {
		this.activeUser = user;
		this.resetUsername();
	}
	
	public void setBook(Book book) {
		this.activeBook = book;
		this.resetBook();
	}
	
	private void displayScreen() {
		resetBook();
		resetUsername();
		refreshPage();
	}
	
	private void refreshPage() {
		if (pageNum <= 0)
			pageNum = 0;
		else if(pageNum > this.activeBook.getNumOfPages()) {
			pageNum = this.activeBook.getNumOfPages();
		}
		//show page
	}
	
	public void turnForward() {
		this.pageNum++;
		refreshPage();
	}
	
	public void turnBackward() {
		this.pageNum--;
		refreshPage();
	}

	private void resetBook() {
		this.pageNum = 0;
	}
	
	private void resetUsername() {
		String name = activeUser.getName();
		//show name
	}
}

class UserReader {
	
	private String name;
	private Membership membership;
	
	public UserReader(Membership membership) {
		this.membership = membership;
	}

	public String getName() {
		return this.name;
	}
}


class Membership {
	private MembershipType type = MembershipType.LIMITED;
	private Date date = new Date();
	
	public Membership() {
	}
	
	public void setMembershipType(MembershipType type) {
		this.type = type;
		this.date = new Date();
	}
}

enum MembershipType {
	LIMITED,
	BASIC,
	PREMIUM
}

class Book {
	
	private int numOfPages;

	public int getNumOfPages() {
		return numOfPages;
	}
	
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class ParkingLot {
	
	private List<ParkingLevel> parkingLevels;
	private Map<Vehicle, ParkingSpot> vehicleParking;
	
	public ParkingLot() {
		this.parkingLevels = new ArrayList<>();
		this.vehicleParking = new HashMap<>();
	}
	
	public void addParkingLevel(ParkingLevel level) {
		this.parkingLevels.add(level);
	}
	
	public boolean park(Vehicle vehicle) {
		return park(vehicle, optimalLevel(vehicle.type));
	}
	
	// say if those vehicles have reservations level-wise
	public boolean park(Vehicle vehicle, ParkingLevel level) {
		ParkingSpot spot = level.findParkingSpot(vehicle.type);
		if (spot == null)
			return false;
		this.vehicleParking.put(vehicle, spot);
		return spot.park(vehicle);
	}
	
	private ParkingLevel optimalLevel(VehicleType type) {
		// some logic
		return null;
	}
	
	public void unpark(Vehicle vehicle) {
		ParkingSpot spot = vehicleParking.get(vehicle);
		spot.free();
		vehicleParking.remove(vehicle);
	}
	
	
}

enum VehicleType {
	MOTORCYCLE,
	CAR, 
	BUS
}

enum ParkingSpotType {
	BIKE,
	COMPACT,
	LARGE
}

abstract class Vehicle {
	protected VehicleType type;
}

class Bus extends Vehicle {
	public Bus() {
		super();
		type = VehicleType.BUS;
	}
}

class Car extends Vehicle {
	public Car() {
		super();
		type = VehicleType.CAR;
	}
}

class Motorcycle extends Vehicle {
	public Motorcycle() {
		super();
		type = VehicleType.MOTORCYCLE;
	}
}

class ParkingSpot {
	
	private int id;
	private boolean isAvailable = true;
	private ParkingSpotType type;
	
	public ParkingSpot(int id, ParkingSpotType type) {
		this.id = id;
		this.type = type;
	}
	
	public boolean park(Vehicle vehicle) {
		return occupy();
	}
	
	public void free() {
		isAvailable = true;
	}

	public int getId() {
		return this.id;
	}
	
	public ParkingSpotType getType() {
		return this.type;
	}
	
	public boolean isAvailable() {
		return this.isAvailable;
	}
	
	private synchronized boolean occupy() {
		return isAvailable() ? !(isAvailable = false) : false;
	}
}

class ParkingLevel {
	
	private Map<ParkingSpotType, LinkedList<ParkingSpot>> parkingSpots;
	
	public ParkingLevel() {
		this.parkingSpots = new HashMap<>();
	}
	
	public void addParkingSpot(ParkingSpot parkingSpot) {
		LinkedList<ParkingSpot> list = this.parkingSpots.getOrDefault(parkingSpot.getType(), new LinkedList<>());
		list.add(parkingSpot);
		this.parkingSpots.put(parkingSpot.getType(), list);
	}
	
	public ParkingSpot findParkingSpot(VehicleType vehicleType) {
		if(vehicleType.equals(VehicleType.MOTORCYCLE)) {
			return findParkingSpotForMotorcycle();
		} else if(vehicleType.equals(VehicleType.CAR)) {
			return findParkingSpotForCar();
		} else if(vehicleType.equals(VehicleType.BUS)) {
			return findParkingSpotForBus();
		} else {
			throw new RuntimeException("Vehicle type not supported");
		}
	}
	
	private ParkingSpot findParkingSpotForMotorcycle() {
		ParkingSpot parkingSpot = findParkingSpot(ParkingSpotType.BIKE);
		if(parkingSpot == null) {
			parkingSpot = findParkingSpotForCar();
		}
		return parkingSpot;
	}
	
	private ParkingSpot findParkingSpotForCar() {
		ParkingSpot parkingSpot = findParkingSpot(ParkingSpotType.COMPACT);
		if(parkingSpot == null) {
			parkingSpot = findParkingSpot(ParkingSpotType.LARGE);
		}
		return parkingSpot;
	}
	
	private ParkingSpot findParkingSpotForBus() {
		LinkedList<ParkingSpot> list = this.parkingSpots.get(ParkingSpotType.LARGE);
		if(list == null) {
			throw new RuntimeException("parking type not supported");
		}
		Iterator<ParkingSpot> parkingSpots = list.iterator();
		while(parkingSpots.hasNext()) {
			ParkingSpot spot = parkingSpots.next();
			if(spot.isAvailable() && canAccommodateBus(spot)) {
				return spot;
			}
		}
		return null;
	}
	
	private boolean canAccommodateBus(ParkingSpot spot) {
		LinkedList<ParkingSpot> list = this.parkingSpots.get(ParkingSpotType.LARGE);
		int index = list.indexOf(spot);
		Iterator<ParkingSpot> consecutiveSpots = list.listIterator(index);
		int size = 5;
		while(consecutiveSpots.hasNext() && size > 0) {
			ParkingSpot nextSpot = consecutiveSpots.next();
			if(!nextSpot.isAvailable() || !nextSpot.getType().equals(ParkingSpotType.LARGE))
				return false;
			size--;
		}
		return size == 0;
	}
	
	private ParkingSpot findParkingSpot(ParkingSpotType type) {
		LinkedList<ParkingSpot> list = this.parkingSpots.get(type);
		if(list == null) {
			throw new RuntimeException("parking type not supported");
		}
		Iterator<ParkingSpot> parkingSpots = list.iterator();
		while(parkingSpots.hasNext()) {
			ParkingSpot parkingSpot = parkingSpots.next();
			if(parkingSpot.isAvailable())
				return parkingSpot;
		}
		return null;
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class JukeBox {
	
	private CDPlayer cdPlayer; 
	
	private Playlist playList;

	
	public JukeBox(CDPlayer cdPlayer) {
		this.cdPlayer = cdPlayer;
		this.playList = cdPlayer.getPlaylist();
		displayPlaylist();
	}

	
	public void play() {
		Iterator<Song> list = this.playList.getIterator();
		while(list.hasNext()) {
			this.cdPlayer.play(list.next());
		}
	}
	
	public void displayPlaylist() {
		Iterator<Song> list = this.playList.getIterator();
		while(list.hasNext()) {
			//show(list.next());
		}
	}
}

class CDPlayer {
	
	private Playlist playlist;
	
	public CDPlayer() {
		this.playlist = new Playlist();
		loadSongs();
	}
	
	private void loadSongs() {
		// code to load from disk into memory
		Song song = new Song();
		this.playlist.addSong(song);
	}
	
	public Playlist getPlaylist() {
		return this.playlist;
	}
	
	public void play(Song song) {
		//read from disk and play
	}
}

class Playlist {
	private List<Song> queue;
	private boolean shuffleOn = false;
	
	public Playlist() {
		queue = new ArrayList<>();
	}
	
	public void addSong(Song song) {
		queue.add(song);
	}
	
	
	public void shuffleOn() {
		this.shuffleOn = true;
	}
	
	public void shuffleOff() {
		this.shuffleOn = false;
	}
	
	public Iterator<Song> getIterator() {
		return queue.iterator();
	}
}

class Song {
	
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


class CallCenter {
	
	private class CallDispatcher extends Thread {
		@Override
		public void run() {
			
			while(CALLS_IN_WAITING != 0) {
				Queue<Call> highPriorityCalls = callQueues.get(CallLevel.SHIT_GOT_REAL);
				Queue<Call> priorityCalls  = callQueues.get(CallLevel.SHIT);
				Queue<Call> issueCalls = callQueues.get(CallLevel.ISSUE);
				
				Queue<Call> toBeProcessed;
				if (!highPriorityCalls.isEmpty()) {
					toBeProcessed = highPriorityCalls;
				} else if(!priorityCalls.isEmpty()) {
					toBeProcessed = priorityCalls;
				} else if(!issueCalls.isEmpty()){
					toBeProcessed = issueCalls;
				} else {
					break;
				}
				
				// not the most accurate approach.. the main thread could addCall/escalateCall
				// with same level before this one 
				// and the available employee will possibly be no longer available
				Call callToBeProcessed = toBeProcessed.peek();
				if(isAvailableEmployee(callToBeProcessed.getCallLevel())) {
					toBeProcessed.poll();
					addCall(callToBeProcessed);
					decrementCallsInWaiting();
				}
			}
			
		}
	}
	
	// probably use BlockingQueue for thread safety
	private static Map<CallLevel, Queue<Call>> callQueues ;
	private static Map<CallLevel, Queue<Employee>> employeeQueues ;
	private static int CALLS_IN_WAITING = 0;
	
	private CallDispatcher callDispatcher = new CallDispatcher();
	
	public CallCenter() {
		callQueues = new HashMap<>();
		employeeQueues = new HashMap<>();
		
		for(CallLevel level : CallLevel.values())
			callQueues.put(level, new LinkedList<>());
		
		for(CallLevel level : CallLevel.values())
			callQueues.put(level, new LinkedList<>());
	}
	
	public synchronized void addCall(Call call) {
		Employee employee = nextAvailableEmployee(call.getCallLevel());
		if(employee == null) {
			addCallInQueue(call);
		} else {
			call.setCallHandler(employee);
		}
	}
	
	private void addCallInQueue(Call call) {
		callQueues.get(call.getCallLevel()).add(call);
		incrementCallsInWaiting();
		
		State state = callDispatcher.getState();
		if(state.equals(State.TERMINATED)) {
			callDispatcher = new CallDispatcher();
		}
		callDispatcher.start();
	}
	
	private synchronized void incrementCallsInWaiting() {
		synchronized (CallCenter.class) {
			CALLS_IN_WAITING++;						
		}
	}
	
	private synchronized void decrementCallsInWaiting() {
		synchronized (CallCenter.class) {
			CALLS_IN_WAITING--;						
		}
	}
	

	public void escalateCall(Call call) {
		if(call.getCallLevel().equals(CallLevel.ISSUE)) {
			call.setCallLevel(CallLevel.SHIT);
		} else if(call.getCallLevel().equals(CallLevel.SHIT)) {
			call.setCallLevel(CallLevel.SHIT_GOT_REAL);
		} else if(call.getCallLevel().equals(CallLevel.SHIT_GOT_REAL)) {
			throw new RuntimeException("Cannot escalate any further.. Hangup the bloody call");
		}
		
		addCall(call);
	}
	
	private boolean isAvailableEmployee(CallLevel callLevel) {
		return !employeeQueues.get(callLevel).isEmpty();
	}
	
	private Employee nextAvailableEmployee(CallLevel callLevel) {
		return employeeQueues.get(callLevel).poll();
	}
	
	public void endCall(Call call) {
		Employee employee = call.getCallHandler();
		Queue<Employee> queue = employeeQueues.get(call.getCallLevel());
		queue.add(employee);
	}
	
	
	
}

enum CallLevel {
	ISSUE,
	SHIT,
	SHIT_GOT_REAL
}

class Call {
	
	private CallLevel level = CallLevel.ISSUE;
	
	private Employee employee;
	
	public Call() {
		
	}
	
	public void setCallLevel(CallLevel level) {
		this.level = level;
	}
	
	public CallLevel getCallLevel() {
		return this.level;
	}
	
	public void setCallHandler(Employee employee) {
		this.employee = employee;
	}
	
	public Employee getCallHandler() {
		return this.employee;
	}
	
}


// employee should not be allowed to instantiate
abstract class Employee {
	
}

class Respondent extends Employee {
	
}

class Manager extends Employee {
	
}

class Director extends Employee {
	
}

enum CardType {
	SPADES,
	HEARTS,
	DIAMONDS,
	CLUBS
}

abstract class Card {
	private final CardType type;
	protected int faceValue;

	public Card(int value, CardType type) {
		this.faceValue = value;
		this.type = type;
	}
	
	@Override
	public String toString() {
		return String.valueOf(type+"-"+faceValue);
	}
	
	public abstract int value();
	public abstract boolean isFaceCard();
	public abstract boolean isAce();
}

class CardDeck<T extends Card> {
	
	private Card[] cards;
	
	public CardDeck() {
		int index = 0;
		this.cards = new Card[52];
		for (CardType card : CardType.values()) {
			for(int i=1; i<= 13; i++) {
				this.cards[index++] = new BlackJackCard(i, card);
			}
		}
		System.out.println(Arrays.toString(cards));
	}
	
	public void shuffle() {
		int last = cards.length-1;
		Random r = new Random();
		while(last > 0) {
			int swapWith = r.nextInt(last+1);
			swapCards(swapWith, last);
			last--;
		}
		System.out.println(Arrays.toString(cards));
	}
	
	private void swapCards(int a, int b) {
		Card temp = cards[a];
		cards[a] = cards[b];
		cards[b] = temp;
	}
	
}

class Hand<T extends Card> {
	
	protected List<T> cards = new ArrayList<>();
	
	public Hand() {
		
	}
	
	public int score() {
		int score = 0;
		
		for (int i=0; i<cards.size(); i++)
			score += cards.get(i).value();
		
		return score;
	}
	
	public void add(T card) {
		cards.add(card);
	}
 }

class BlackJackHand extends Hand<BlackJackCard> {
	
	@Override
	public int score() {
		List<Integer> scores = allPossibleScores();
		int minOver = Integer.MAX_VALUE;
		int maxUnder = Integer.MIN_VALUE;
		for(int score : scores) {
			if (score <= 21 && score > maxUnder) {
				maxUnder = score;
			} else if (score > 21 && score < minOver) {
				minOver = score;
			}
		}
		
		return maxUnder == Integer.MIN_VALUE ? minOver : maxUnder;
	}
	
	private List<Integer> allPossibleScores() {
		boolean hasAce = false;
		int sum = 0;
		for(Card card : this.cards) {
			if(card.isAce()) {
				hasAce = true;
			}
			sum += card.value();
		}
		List<Integer> scores = new ArrayList<>();
		scores.add(sum);
		if (hasAce) {
			scores.add(sum + 10); // Ace can mean 1 or 11
		}
		return scores;
	}
}

class BlackJackCard extends Card {

	public BlackJackCard(int value, CardType type) {
		super(value, type);
	}

	@Override
	public int value() {
		if(isAce())
			return 1;
		else if(isFaceCard())
			return 10;
		
		return this.faceValue;
	}

	@Override
	public boolean isFaceCard() {
		return this.faceValue > 10 && this.faceValue <= 13;
	}

	@Override
	public boolean isAce() {
		return this.faceValue == 1;
	}
	
}
