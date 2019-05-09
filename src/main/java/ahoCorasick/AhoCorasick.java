package ahoCorasick;

import java.util.Iterator;

/**
 * jon:
 *
 * The main modifications from the original is to make this fully type-parameterized.
 */

/**
 * <p>
 * An implementation of the Aho-Corasick string searching automaton. This
 * implementation of the <a
 * href="http://portal.acm.org/citation.cfm?id=360855&dl=ACM&coll=GUIDE"
 * target="_blank">Aho-Corasick</a> algorithm is optimized to work with bytes.
 * </p>
 *
 * <p>
 * Example usage: <code><pre>
 AhoCorasick tree = new AhoCorasick();
 tree.add("hello".getBytes(), "hello");
 tree.add("world".getBytes(), "world");
 tree.prepare();

 Iterator searcher = tree.search("hello world".getBytes());
 while (searcher.hasNext()) {
 SearchResult result = searcher.next();
 System.out.println(result.getOutputs());
 System.out.println("Found at index: " + result.getLastIndex());
 }
 </pre></code>
 * </p>
 *
 * <h2>Recent changes</h2>
 * <ul>
 *
 * <li>Per user request from Carsten Kruege, I've changed the signature of
 * State.getOutputs() and SearchResults.getOutputs() to Sets rather than Lists.</li>
 *
 * </ul>
 *
 * jon: tweaked to be type generic
 */
public class AhoCorasick<T> {
    private State<T> root;
    private boolean prepared;

    public AhoCorasick(Tree root) {
        this.root = root.getRoot();
        this.prepared = root.getPrepared();
    }


    /**
     * Starts a new search, and returns an Iterator of SearchResults.
     */
    public Iterator<SearchResult<T>> search(byte[] bytes) {
        return new Searcher<T>(this, this.startSearch(bytes));
    }

    /**
     * Begins a new search using the raw interface. Package protected.
     */
    public SearchResult<T> startSearch(byte[] bytes) {
        if (!this.prepared)
            throw new IllegalStateException("can't start search until prepare()");
        return continueSearch(new SearchResult<T>(this.root, bytes, 0));
    }

    /**
     * Continues the search, given the initial state described by the lastResult.
     * Package protected.
     */
    public SearchResult<T> continueSearch(SearchResult<T> lastResult) {
        byte[] bytes = lastResult.bytes;
        State<T> state = lastResult.lastMatchedState;
        for (int i = lastResult.lastIndex; i < bytes.length; i++) {
            byte b = bytes[i];
            while (state.get(b) == null)
                state = state.getFail();
            state = state.get(b);
            if (state.getOutputs().size() > 0)
                return new SearchResult<T>(state, bytes, i + 1);
        }
        return null;
    }

}

