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

package com.vladsch.flexmark.ext.gfm.tables;

import com.vladsch.flexmark.ast.VisitHandler;

public interface TableVisitor {
    static <V extends TableVisitor> VisitHandler<?>[] VISIT_HANDLERS(V visitor) {
        return new VisitHandler<?>[] {
                new VisitHandler<>(TableBlock.class, visitor::visit),
                new VisitHandler<>(TableHead.class, visitor::visit),
                new VisitHandler<>(TableSeparator.class, visitor::visit),
                new VisitHandler<>(TableBody.class, visitor::visit),
                new VisitHandler<>(TableRow.class, visitor::visit),
                new VisitHandler<>(TableCell.class, visitor::visit),
        };
    }

    void visit(TableBlock node);
    void visit(TableHead node);
    void visit(TableSeparator node);
    void visit(TableBody node);
    void visit(TableRow node);
    void visit(TableCell node);
}
