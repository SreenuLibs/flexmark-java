/*
 * Copyright (c) 2015-2016 Vladimir Schneider <vladimir.schneider@gmail.com>, all rights reserved.
 *
 * This code is private property of the copyright holder and cannot be used without
 * having obtained a license or prior written permission of the of the copyright holder.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;

import java.util.Objects;
import java.util.Stack;
import java.util.function.Consumer;

public class DescendantNodeIterator implements ReversiblePeekingIterator<Node> {
    final private boolean isReversed;
    private ReversiblePeekingIterator<Node> iterator;
    private Stack<ReversiblePeekingIterator<Node>> iteratorStack;
    private Node result;

    /**
     * iterate nodes, with descendants, depth first until all are done
     *
     * @param iterator iterator to use for iterating nodes and their descendants
     */
    public DescendantNodeIterator(ReversiblePeekingIterator<Node> iterator) {
        this.isReversed = iterator.isReversed();
        this.iterator = iterator instanceof DescendantNodeIterator ? ((DescendantNodeIterator) iterator).iterator : iterator;
        this.iteratorStack = null;
        this.result = null;
    }

    @Override
    public boolean isReversed() {
        return isReversed;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Node next() {
        result = iterator.next();

        if (result.getFirstChild() != null) {
            // push the current iterator on to the stack and make the node's children the iterator
            if (iterator.hasNext()) {
                if (iteratorStack == null) iteratorStack = new Stack<>();
                iteratorStack.push(iterator);
            }

            iterator = isReversed ? result.getReversedChildIterator() : result.getChildIterator();
        } else {
            // see if need to pop an iterator
            if (iteratorStack != null && !iteratorStack.isEmpty() && !iterator.hasNext()) {
                // pop a new iterator off the stack
                iterator = iteratorStack.pop();
            }
        }

        return result;
    }

    public Node peek() {
        return iterator.peek();
    }

    @Override
    public void remove() {
        if (result == null) {
            throw new IllegalStateException("Either next() was not called yet or the node was removed");
        }
        result.unlink();
        result = null;
    }

    @Override
    public void forEachRemaining(Consumer<? super Node> consumer) {
        Objects.requireNonNull(consumer);

        while (hasNext()) {
            consumer.accept(next());
        }
    }
}
