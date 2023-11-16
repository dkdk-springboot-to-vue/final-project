<!-- FormRow.vue -->
<template>
    <div class="form-row">
        <label class="label">{{ label }}</label>
        <div class="input-container">
            <input :type="type" :value="value" @input="$emit('update:modelValue', $event)" />
        </div>
        <p class="error-text">{{ error }}</p>
    </div>
</template>
  
<script>
export default {
    props: {
        label: String,
        type: {
            type: String,
            default: 'text',
        },
        value: String,
        error: String,
    },
};
</script>
  
<style scoped>
.form-row {
    display: flex;
    flex-direction: column;
    margin-bottom: 20px;
}

.label {
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 8px;
    color: #333;
}

.input-container {
    width: 100%;
}

input {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 6px;
    box-sizing: border-box;
    transition: border-color 0.3s;
}

input:focus {
    outline: none;
    border-color: #4a90e2;
}

.error-text {
    margin-top: 8px;
    font-size: 14px;
    color: red;
}
</style>