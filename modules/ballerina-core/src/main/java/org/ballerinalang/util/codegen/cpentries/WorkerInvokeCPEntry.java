/*
 * Copyright (c) 2017, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 * <p>
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ballerinalang.util.codegen.cpentries;

import org.ballerinalang.model.types.BType;

import java.util.Arrays;

/**
 * {@code WorkerInvokeCPEntry} represents a Ballerina worker invocation in the constant pool.
 *
 * @since 0.90
 */
public class WorkerInvokeCPEntry implements ConstantPoolEntry {
    BType[] bTypes;
    // Registers which contains worker incoming arguments
    private int[] argRegs;

    // Registers to which return  values to be copied
    private int[] retRegs;

    public WorkerInvokeCPEntry(int[] argRegs, int[] retRegs, BType[] btypes) {
        this.argRegs = argRegs;
        this.retRegs = retRegs;
        this.bTypes = btypes;
    }

    public int[] getArgRegs() {
        return argRegs;
    }

    public int[] getRetRegs() {
        return retRegs;
    }

    public BType[] getbTypes() {
        return bTypes;
    }

    public ConstantPoolEntry.EntryType getEntryType() {
        return EntryType.CP_ENTRY_WORKER_INVOKE;
    }

    @Override
    public int hashCode() {
        int[] combined = new int[argRegs.length + retRegs.length];
        System.arraycopy(argRegs, 0, combined, 0, argRegs.length);
        System.arraycopy(retRegs, 0, combined, argRegs.length, retRegs.length);
        return Arrays.hashCode(combined);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof WorkerInvokeCPEntry && Arrays.equals(argRegs, ((WorkerInvokeCPEntry) obj).argRegs)
                && Arrays.equals(retRegs, ((WorkerInvokeCPEntry) obj).retRegs);
    }
}
