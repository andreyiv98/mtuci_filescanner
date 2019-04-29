package ahoCorasick;

public class Tree<T> {
    private State<T> root;
    private boolean prepared;

    public Tree() {
        this.root = new State<T>(0);
        this.prepared = false;
    }

    /**
     * Adds a new keyword with the given output. During search, if the keyword is
     * matched, output will be one of the yielded elements in
     * SearchResults.getOutputs().
     */
    public void add(byte[] keyword, T output) {
        if (this.prepared)
            throw new IllegalStateException(
                    "can't add keywords after prepare() is called");
        State<T> lastState = this.root.extendAll(keyword);
        lastState.addOutput(output);
    }

    /**
     * Prepares the automaton for searching. This must be called before any
     * searching().
     */
    public void prepare() {
        this.prepareFailTransitions();
        this.prepared = true;
    }

    /**
     * Sets all the out transitions of the root to itself, if no transition yet
     * exists at this point.
     */
    private void prepareRoot() {
        for (int i = 0; i < 256; i++)
            if (this.root.get((byte) i) == null)
                this.root.put((byte) i, this.root);
    }

    /**
     * DANGER DANGER: dense algorithm code ahead. Very order dependent.
     * Initializes the fail transitions of all states except for the root.
     */
    private void prepareFailTransitions() {
        Queue<T> q = new Queue<T>();
        for (int i = 0; i < 256; i++)
            if (this.root.get((byte) i) != null) {
                this.root.get((byte) i).setFail(this.root);
                q.add(this.root.get((byte) i));
            }
        this.prepareRoot();
        while (!q.isEmpty()) {
            State<T> state = q.pop();
            byte[] keys = state.keys();
            for (int i = 0; i < keys.length; i++) {
                State<T> r = state;
                byte a = keys[i];
                State<T> s = r.get(a);
                q.add(s);
                r = r.getFail();
                while (r.get(a) == null)
                    r = r.getFail();
                s.setFail(r.get(a));
                s.getOutputs().addAll(r.get(a).getOutputs());
            }
        }
    }

    /**
     * Returns the root of the tree. Package protected, since the user probably
     * shouldn't touch this.
     */
    public State<T> getRoot() {
        return this.root;
    }

    public boolean getPrepared(){
        return this.prepared;
    }

}
