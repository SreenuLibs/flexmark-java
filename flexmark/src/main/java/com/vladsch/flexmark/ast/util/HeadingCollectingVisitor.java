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

package com.vladsch.flexmark.ast.util;

import com.vladsch.flexmark.ast.*;

import java.util.ArrayList;

public class HeadingCollectingVisitor {
    final private ArrayList<Heading> headings = new ArrayList<>();
    final private NodeVisitor myVisitor;

    public HeadingCollectingVisitor() {
        myVisitor = new BlockNodeVisitor(
                new VisitHandler<>(Heading.class, headings::add)
        );
    }
    
    public void collect(Node node) {
        myVisitor.visit(node);
    }

    public ArrayList<Heading> collectAndGetHeadings(Node node) {
        myVisitor.visit(node);
        return headings;
    }

    public ArrayList<Heading> getHeadings() {
        return headings;
    }
}
