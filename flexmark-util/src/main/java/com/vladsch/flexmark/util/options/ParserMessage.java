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

package com.vladsch.flexmark.util.options;

import com.vladsch.flexmark.util.sequence.BasedSequence;

public class ParserMessage {
    protected final BasedSequence source; 
    protected final ParsedOptionStatus status; 
    protected final String message;

    public ParserMessage(BasedSequence source, ParsedOptionStatus status, String message) {
        this.source = source;
        this.status = status;
        this.message = message;
    }

    public BasedSequence getSource() {
        return source;
    }

    public ParsedOptionStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
